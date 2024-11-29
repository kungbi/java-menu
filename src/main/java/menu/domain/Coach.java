package menu.domain;

import java.util.Objects;

public class Coach {
    private final String name;
    private final DislikeMenu dislikeMenu;

    public Coach(String name, DislikeMenu dislikeMenu) {
        if (!(2 <= name.length() && name.length() <= 4)) {
            throw new IllegalArgumentException("코치의 이름은 최소 2글자, 최대 4글자 입니다.");
        }
        this.dislikeMenu = dislikeMenu;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isDislike(Menu menu) {
        return this.dislikeMenu.containsMenu(menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coach other = (Coach) obj;
        return Objects.equals(name, other.name);
    }
}
