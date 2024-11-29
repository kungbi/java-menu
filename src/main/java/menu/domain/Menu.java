package menu.domain;

import java.util.Objects;

public class Menu {
    private final String name;
    private final Category category;

    public Menu(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Menu{" +
               "name='" + name + '\'' +
               ", category=" + category +
               '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Menu other = (Menu) obj;
        return name.equals(other.name) && category == other.category;
    }
}
