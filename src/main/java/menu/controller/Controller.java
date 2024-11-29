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
        Coaches coaches = retryInputUtil.getCoaches();
        for (Coach coach : coaches.getCoaches()) {
            List<Menu> dislikeMenus = retryInputUtil.getDislikeMenus(coach.getName());
            coach.setDislikeMenu(dislikeMenus);
        }

        DailyMenu dailyMenu = new DailyMenu();
        for (DayOfWeek dayOfWeek : DayOfWeek.getDaysOfWeek()) {
            dailyMenu.setCategory(dayOfWeek, Category.getRandomCategory());
        }

        for (Coach coach : coaches.getCoaches()) {
            for (DayOfWeek dayOfWeek : DayOfWeek.getDaysOfWeek()) {
                Category category = dailyMenu.getCategory(dayOfWeek);
                while (true) {
                    try {
                        String randomMenuInCategory = menuRepository.getRandomMenuInCategory(category);
                        Optional<Menu> foundMenu = menuRepository.findByName(randomMenuInCategory);
                        if (foundMenu.isEmpty()) {
                            throw new IllegalStateException("없는 메뉴입니다.");
                        }
                        dailyMenu.setDailyMenu(coach, dayOfWeek, foundMenu.get());
                        break;
                    } catch (IllegalArgumentException error) {
//                        OutputView.printError(error.getMessage());
                    }
                }
            }
        }

        RecommendedMenus recommendedMenus = new RecommendedMenus(dailyMenu.getCategories(), dailyMenu.getDailyMenus());
        OutputView.printRecommendedMenus(recommendedMenus);
    }

}
