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
        OutputView.printWelcome();

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
        for (DayOfWeek dayOfWeek : DayOfWeek.getDaysOfWeek()) {
            setDailyMenu(dailyMenu, dayOfWeek, coaches);
        }

    }

    private void setDailyMenu(DailyMenu dailyMenu, DayOfWeek dayOfWeek, Coaches coaches) {
        for (Coach coach : coaches.getCoaches()) {
            setMenuForDay(dailyMenu, coach, dayOfWeek);
        }
    }

    private void setMenuForDay(DailyMenu dailyMenu, Coach coach, DayOfWeek dayOfWeek) {
        Category category = dailyMenu.getCategory(dayOfWeek);

        while (true) {
            try {
                String randomMenuName = menuRepository.getRandomMenuInCategory(category);
                Optional<Menu> foundMenu = menuRepository.findByName(randomMenuName);
                if (foundMenu.isEmpty()) {
                    throw new IllegalStateException();
                }
                dailyMenu.setDailyMenu(coach, dayOfWeek, foundMenu.get());
                break; // 성공적으로 메뉴를 설정했으면 반복 종료
            } catch (IllegalArgumentException e) {
            }
        }
    }


    private void setCategories(DailyMenu dailyMenu) {
        for (DayOfWeek dayOfWeek : DayOfWeek.getDaysOfWeek()) {
            setCategory(dailyMenu, dayOfWeek);
        }
    }

    private void setCategory(DailyMenu dailyMenu, DayOfWeek dayOfWeek) {
        while (true) {
            try {
                Category randomCategory = Category.getRandomCategory();
                dailyMenu.setCategory(dayOfWeek, randomCategory);
                break;
            } catch (IllegalArgumentException e) {
            }
        }
    }

}
