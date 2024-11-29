package menu.controller;

import java.util.List;
import java.util.Optional;
import menu.domain.Category;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.DailyMenu;
import menu.domain.DayOfWeek;
import menu.domain.Menu;
import menu.dtos.RecommendedMenus;
import menu.repository.MenuRepository;
import menu.view.OutputView;

public class Controller {
    private final RetryInputUtil retryInputUtil;
    private final MenuRepository menuRepository;

    public Controller(RetryInputUtil retryInputUtil, MenuRepository menuRepository) {
        this.retryInputUtil = retryInputUtil;
        this.menuRepository = menuRepository;
    }

    public void run() {
        // 입력
        Coaches coaches = retryInputUtil.getCoaches();
        for (Coach coach : coaches.getCoaches()) {
            List<Menu> dislikeMenus = retryInputUtil.getDislikeMenus(coach.getName());
            coach.setDislikeMenu(dislikeMenus);
        }

        DailyMenu dailyMenu = new DailyMenu();
        setCategories(dailyMenu);
        setMenus(coaches, dailyMenu);

        RecommendedMenus recommendedMenus = new RecommendedMenus(dailyMenu.getCategories(), dailyMenu.getDailyMenus());
        OutputView.printRecommendedMenus(recommendedMenus);
    }

    private void setMenus(Coaches coaches, DailyMenu dailyMenu) {
        for (Coach coach : coaches.getCoaches()) {
            setDailyMenu(dailyMenu, coach);
        }
    }

    private void setDailyMenu(DailyMenu dailyMenu, Coach coach) {
        for (DayOfWeek dayOfWeek : DayOfWeek.getDaysOfWeek()) {
            setMenuForDay(dailyMenu, coach, dayOfWeek);
        }
    }

    private void setMenuForDay(DailyMenu dailyMenu, Coach coach, DayOfWeek dayOfWeek) {
        Category category = dailyMenu.getCategory(dayOfWeek);

        while (true) {
            try {
                Menu menu = getValidMenuInCategory(category);
                dailyMenu.setDailyMenu(coach, dayOfWeek, menu);
                break; // 성공적으로 메뉴를 설정했으면 반복 종료
            } catch (IllegalArgumentException e) {
                // 무시하고 반복 지속
            }
        }
    }

    private Menu getValidMenuInCategory(Category category) {
        String randomMenuName = menuRepository.getRandomMenuInCategory(category);
        Optional<Menu> foundMenu = menuRepository.findByName(randomMenuName);

        // 유효한 메뉴가 없으면 예외 발생
        return foundMenu.orElseThrow(() -> new IllegalStateException("없는 메뉴입니다."));
    }


    private static void setCategories(DailyMenu dailyMenu) {
        for (DayOfWeek dayOfWeek : DayOfWeek.getDaysOfWeek()) {
            dailyMenu.setCategory(dayOfWeek, Category.getRandomCategory());
        }
    }

}
