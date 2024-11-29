package menu.repository;

import java.util.List;
import java.util.Optional;
import menu.domain.Category;
import menu.domain.Menu;

public class MenuRepository implements Repository<Menu> {
    List<Menu> menus;

    @Override
    public void add(Menu menu) {
        menus.add(menu);
    }

    public Optional<Menu> findByCategoryAndName(Category category, String name) {
        for (Menu menu : menus) {
            if (menu.getCategory().equals(category) && menu.getName().equals(name)) {
                return Optional.of(menu);
            }
        }
        return Optional.empty();
    }

    public boolean existsByCategoryAndName(Category category, String name) {
        return this.findByCategoryAndName(category, name).isPresent();
    }
}
