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

    public String getRandomMenuInCategory(Category category) {
        List<String> menuByCategory = this.getMenuByCategory(category);
        List<String> shuffled = Randoms.shuffle(menuByCategory);
        return shuffled.get(0);
    }

    private List<String> getMenuByCategory(Category category) {
        List<String> filteredMenus = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getCategory() == category) {
                filteredMenus.add(menu.getName());
            }
        }
        return filteredMenus;
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
