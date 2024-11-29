package menu.domain;

import java.util.List;

public class DislikeMenu {
    private final List<Menu> dislikeMenu;

    public DislikeMenu(List<Menu> dislikeMenu) {
        if (dislikeMenu.stream().distinct().count() != dislikeMenu.size()) {
            throw new IllegalArgumentException("메뉴가 중복될 수 없습니다.");
        }
        if (2 < dislikeMenu.size()) {
            throw new IllegalArgumentException("최대 두개까지 설정 가능합니다.");
        }
        this.dislikeMenu = dislikeMenu;
    }

    public boolean containsMenu(Menu menu) {
        return dislikeMenu.contains(menu);
    }
}
