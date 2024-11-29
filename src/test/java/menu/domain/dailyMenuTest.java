package menu.domain;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class dailyMenuTest {
    DailyMenu dailyMenu;

    @BeforeEach
    void setUp() {
        dailyMenu = new DailyMenu();
    }


    @Nested
    class 카테고리_테스트 {
        @Test
        void 카테고리_설정_테스트() {
            dailyMenu.setCategory(DayOfWeek.MONDAY, Category.KOREAN);
            dailyMenu.setCategory(DayOfWeek.TUESDAY, Category.KOREAN);
            dailyMenu.setCategory(DayOfWeek.WEDNESDAY, Category.CHINESE);
            dailyMenu.setCategory(DayOfWeek.THURSDAY, Category.JAPANESE);
            dailyMenu.setCategory(DayOfWeek.FRIDAY, Category.ASIAN);

            Assertions.assertThat(dailyMenu.getCategorySize()).isEqualTo(5);
        }

        @Test
        void 카테고리_3개_설정_테스트() {
            dailyMenu.setCategory(DayOfWeek.MONDAY, Category.KOREAN);
            dailyMenu.setCategory(DayOfWeek.TUESDAY, Category.KOREAN);

            Assertions.assertThatThrownBy(() -> dailyMenu.setCategory(DayOfWeek.WEDNESDAY, Category.KOREAN))
                    .isInstanceOf(
                            IllegalArgumentException.class);
        }
    }


    @Nested
    class 메뉴_추가_테스트 {
        @Test
        void 데일리_메뉴_설정() {
            Coach coach = new Coach("코치1", new DislikeMenu(List.of()));
            Menu menu1 = new Menu("메뉴1", Category.ASIAN);
            Menu menu2 = new Menu("메뉴2", Category.ASIAN);
            Menu menu3 = new Menu("메뉴3", Category.JAPANESE);
            Menu menu4 = new Menu("메뉴4", Category.JAPANESE);
            Menu menu5 = new Menu("메뉴5", Category.CHINESE);

            // when
            dailyMenu.setCategory(DayOfWeek.MONDAY, Category.ASIAN);
            dailyMenu.setDailyMenu(coach, DayOfWeek.MONDAY, menu1);

            dailyMenu.setCategory(DayOfWeek.TUESDAY, Category.ASIAN);
            dailyMenu.setDailyMenu(coach, DayOfWeek.TUESDAY, menu2);

            dailyMenu.setCategory(DayOfWeek.WEDNESDAY, Category.JAPANESE);
            dailyMenu.setDailyMenu(coach, DayOfWeek.WEDNESDAY, menu3);

            dailyMenu.setCategory(DayOfWeek.THURSDAY, Category.JAPANESE);
            dailyMenu.setDailyMenu(coach, DayOfWeek.THURSDAY, menu4);

            dailyMenu.setCategory(DayOfWeek.FRIDAY, Category.CHINESE);
            dailyMenu.setDailyMenu(coach, DayOfWeek.FRIDAY, menu5);

            // then
            Map<DayOfWeek, Menu> dailyMenuResult = dailyMenu.getDailyMenu(coach);
            Assertions.assertThat(dailyMenuResult.size()).isEqualTo(5);
            Assertions.assertThat(dailyMenuResult.get(DayOfWeek.MONDAY)).isEqualTo(menu1);
        }

        @Test
        void 데일리_메뉴_중복된_메뉴() {
            Coach coach = new Coach("코치1", new DislikeMenu(List.of()));
            Menu menu1 = new Menu("메뉴1", Category.ASIAN);
            Menu menu2 = new Menu("메뉴1", Category.ASIAN);
            dailyMenu.setCategory(DayOfWeek.MONDAY, Category.ASIAN);
            dailyMenu.setCategory(DayOfWeek.THURSDAY, Category.ASIAN);
            dailyMenu.setDailyMenu(coach, DayOfWeek.MONDAY, menu1);

            // when
            Throwable exception = Assertions.catchThrowable(
                    () -> dailyMenu.setDailyMenu(coach, DayOfWeek.TUESDAY, menu2));

            // then
            Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 설정된_카테고리와_다른_메뉴_설정() {
            Coach coach = new Coach("코치1", new DislikeMenu(List.of()));
            dailyMenu.setCategory(DayOfWeek.MONDAY, Category.KOREAN);
            Menu menu1 = new Menu("메뉴1", Category.CHINESE);

            // when
            Throwable exception = Assertions.catchThrowable(
                    () -> dailyMenu.setDailyMenu(coach, DayOfWeek.MONDAY, menu1));

            // then
            Assertions.assertThat(exception).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 먹지_못하는_메뉴_추가() {
            Menu dislikeMenu = new Menu("못먹는_메뉴", Category.ASIAN);
            Coach coach = new Coach("코치1", new DislikeMenu(List.of(dislikeMenu)));
            dailyMenu.setCategory(DayOfWeek.MONDAY, Category.KOREAN);

            // when
            Throwable exception = Assertions.catchThrowable(
                    () -> dailyMenu.setDailyMenu(coach, DayOfWeek.MONDAY, dislikeMenu));

            // then
            Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }
    }

}