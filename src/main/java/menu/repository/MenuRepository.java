package menu.repository;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import menu.domain.Category;
import menu.domain.Menu;

public class MenuRepository implements Repository<Menu> {
    private final List<Menu> menus = new ArrayList<>();

    @Override
    public void add(Menu menu) {
        this.menus.add(menu);
    }

    public Menu getRandomMenuInCategory(Category category) {
        List<Menu> menuByCategory = this.getMenuByCategory(category);
        return Randoms.shuffle(menuByCategory).getFirst();
    }

    private List<Menu> getMenuByCategory(Category category) {
        List<Menu> filteredMenus = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getCategory().equals(category)) {
                filteredMenus.add(menu);
            }
        }
        return List.copyOf(filteredMenus);
    }

    public Optional<Menu> findByName(String name) {
        for (Menu menu : menus) {
            if (menu.getName().equals(name)) {
                return Optional.of(menu);
            }
        }
        return Optional.empty();
    }

    public boolean existsByName(String name) {
        return this.findByName(name).isPresent();
    }
}
