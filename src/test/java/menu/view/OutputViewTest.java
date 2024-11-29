package menu.view;

import java.util.Map;
import menu.domain.Category;
import menu.domain.Coach;
import menu.domain.DayOfWeek;
import menu.domain.Menu;
import menu.dtos.RecommendedMenus;
import org.junit.jupiter.api.Test;

class OutputViewTest {

    @Test
    void 결과_출력() {
        RecommendedMenus recommendedMenus = new RecommendedMenus(
                Map.of(
                        DayOfWeek.MONDAY, Category.KOREAN,
                        DayOfWeek.TUESDAY, Category.KOREAN,
                        DayOfWeek.WEDNESDAY, Category.JAPANESE,
                        DayOfWeek.THURSDAY, Category.ASIAN,
                        DayOfWeek.FRIDAY, Category.CHINESE
                ),
                Map.of(
                        new Coach("코치1"),
                        Map.of(
                                DayOfWeek.MONDAY, new Menu("음식1", Category.KOREAN),
                                DayOfWeek.TUESDAY, new Menu("음식2", Category.KOREAN),
                                DayOfWeek.WEDNESDAY, new Menu("음식3", Category.KOREAN),
                                DayOfWeek.THURSDAY, new Menu("음식4", Category.KOREAN),
                                DayOfWeek.FRIDAY, new Menu("음식5", Category.KOREAN)
                        )
                )
        );
        OutputView.printRecommendedMenus(recommendedMenus);
    }

}