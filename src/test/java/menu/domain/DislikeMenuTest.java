package menu.domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DislikeMenuTest {

    static Stream<Arguments> 객체_생성_예외_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Menu("a", Category.ASIAN),
                                new Menu("b", Category.ASIAN),
                                new Menu("c", Category.ASIAN)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Menu("a", Category.ASIAN),
                                new Menu("a", Category.ASIAN)
                        )
                )
        );
    }

    @Test
    void 객체_생성_테스트() {
        List<Menu> menus = List.of(
                new Menu("a", Category.ASIAN),
                new Menu("b", Category.ASIAN)
        );

        // when
        DislikeMenu dislikeMenu = new DislikeMenu(menus);

        // then
        Assertions.assertThat(dislikeMenu).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("객체_생성_예외_테스트_케이스")
    void 객체_생성_예외(List<Menu> menus) {
        Assertions.assertThatThrownBy(() -> new DislikeMenu(menus)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 포함되는_음식_확인_테스트() {
        // given
        List<Menu> menus = List.of(
                new Menu("a", Category.ASIAN),
                new Menu("b", Category.ASIAN)
        );
        DislikeMenu dislikeMenu = new DislikeMenu(menus);

        // when
        boolean result = dislikeMenu.containsMenu(new Menu("a", Category.ASIAN));

        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void 포함되지_않는_음식_확인_테스트() {
        // given
        List<Menu> menus = List.of(
                new Menu("a", Category.ASIAN),
                new Menu("b", Category.ASIAN)
        );
        DislikeMenu dislikeMenu = new DislikeMenu(menus);

        // when
        boolean result = dislikeMenu.containsMenu(new Menu("a", Category.KOREAN));

        // then
        Assertions.assertThat(result).isFalse();
    }



}