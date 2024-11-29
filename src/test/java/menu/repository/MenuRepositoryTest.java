package menu.repository;


import menu.domain.Category;
import menu.domain.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MenuRepositoryTest {

    @Test
    void 랜덤_음식_뽑기_테스트() {
        // given
        MenuRepository menuRepository = new MenuRepository();
        menuRepository.add(new Menu("a", Category.ASIAN));
        menuRepository.add(new Menu("b", Category.ASIAN));
        menuRepository.add(new Menu("c", Category.ASIAN));
        menuRepository.add(new Menu("d", Category.ASIAN));

        // when
        Menu randomMenuInCategory = menuRepository.getRandomMenuInCategory(Category.ASIAN);

        // then
        Assertions.assertThat(randomMenuInCategory).isNotNull();
        System.out.println(randomMenuInCategory);
    }

}