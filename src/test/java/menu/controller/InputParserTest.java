package menu.controller;

import java.util.List;
import menu.domain.Category;
import menu.domain.Coaches;
import menu.domain.Menu;
import menu.repository.MenuRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InputParserTest {
    InputParser inputParser;

    @BeforeEach
    void setUp() {
        MenuRepository menuRepository = new MenuRepository();
        menuRepository.add(new Menu("메뉴1", Category.ASIAN));
        menuRepository.add(new Menu("메뉴2", Category.ASIAN));
        inputParser = new InputParser(menuRepository);
    }

    @Test
    void 코치_이름_파싱() {
        Coaches coaches = inputParser.parseCoaches("코치1, 코치2");

        Assertions.assertThat(coaches.getSize()).isEqualTo(2);
    }

    @Test
    void 음식_파싱() {
        List<Menu> menus = inputParser.parseMenus("메뉴1,메뉴2");

        Assertions.assertThat(menus.size()).isEqualTo(2);
    }


}