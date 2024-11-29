package menu.view;

import java.util.List;
import java.util.Map;
import menu.domain.Category;
import menu.domain.Coach;
import menu.domain.DayOfWeek;
import menu.domain.Menu;
import menu.dtos.RecommendedMenus;

public class OutputView {

    public static void printWelcome() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
        printNewLine();
    }

    public static void printRecommendedMenus(RecommendedMenus recommendedMenus) {
        Map<Coach, Map<DayOfWeek, Menu>> coachMapMap = recommendedMenus.recommendedMenus();

        System.out.println("메뉴 추천 결과입니다.");

        printDaysOfWeek();
        printCategories(recommendedMenus.categories());
        for (Coach coach : coachMapMap.keySet()) {
            printMenus(coach, coachMapMap.get(coach));
        }

        printNewLine();
        System.out.println("추천을 완료했습니다.");
    }

    public static void printError(String message) {
        System.out.println("[ERROR] " + message);
        printNewLine();
    }

    private static void printDaysOfWeek() {
        List<DayOfWeek> daysOfWeek = DayOfWeek.getDaysOfWeek();

        System.out.print("[ 구분 | ");
        for (int i = 0; i < daysOfWeek.size(); i++) {
            System.out.printf(daysOfWeek.get(i).getName());
            if (i < daysOfWeek.size() - 1) {
                System.out.print(" | ");
            }
        }
        System.out.print(" ]");
        printNewLine();
    }

    private static void printCategories(Map<DayOfWeek, Category> categories) {
        List<DayOfWeek> daysOfWeek = DayOfWeek.getDaysOfWeek();

        System.out.print("[ 카테고리 | ");
        for (int i = 0; i < daysOfWeek.size(); i++) {
            DayOfWeek dayOfWeek = daysOfWeek.get(i);
            System.out.printf(categories.get(dayOfWeek).getName());
            if (i < daysOfWeek.size() - 1) {
                System.out.print(" | ");
            }
        }
        System.out.print(" ]");
        printNewLine();
    }

    private static void printMenus(Coach coach, Map<DayOfWeek, Menu> menus) {
        List<DayOfWeek> daysOfWeek = DayOfWeek.getDaysOfWeek();

        System.out.printf("[ %s | ", coach.getName());
        for (int i = 0; i < daysOfWeek.size(); i++) {
            DayOfWeek dayOfWeek = daysOfWeek.get(i);
            System.out.printf(menus.get(dayOfWeek).getName());
            if (i < daysOfWeek.size() - 1) {
                System.out.print(" | ");
            }
        }
        System.out.print(" ]");
        printNewLine();
    }

    private static void printNewLine() {
        System.out.println();
    }
}
