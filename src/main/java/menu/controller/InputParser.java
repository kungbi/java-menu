package menu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.Menu;
import menu.repository.MenuRepository;

public class InputParser {
    private final MenuRepository menuRepository;

    public InputParser(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Coaches parseCoaches(String input) {
        List<Coach> coaches = new ArrayList<>();
        for (String splitInput : input.split(",")) {
            String coachName = splitInput.trim();
            coaches.add(new Coach(coachName));
        }
        return new Coaches(coaches);
    }

    public List<Menu> parseMenus(String input) {
        List<Menu> menus = new ArrayList<>();
        for (String splitInput : input.split(",")) {
            String menuName = splitInput.trim();
            if (menuName.isEmpty() || menuName.isBlank()) {
                return List.of();
            }
            Optional<Menu> foundMenu = menuRepository.findByName(menuName);
            if (foundMenu.isEmpty()) {
                throw new IllegalArgumentException("존재하지 않는 메뉴입니다.");
            }
            menus.add(foundMenu.get());
        }
        return menus;
    }
}
