package menu.domain;

import java.util.HashMap;
import java.util.Map;

public class DailyMenu {
    private final Map<DayOfWeek, Category> categories = new HashMap<>();
    private final Map<Coach, Map<DayOfWeek, Menu>> dailyMenu = new HashMap<>();
    private final DislikeMenu dislikeMenus;

    public DailyMenu(DislikeMenu dislikeMenus) {
        this.dislikeMenus = dislikeMenus;
    }

    public void setCategory(DayOfWeek dayOfWeek, Category category) {
        if (this.categories.containsKey(dayOfWeek)) {
            throw new IllegalStateException("이미 설정된 요일 입니다.");
        }
        if (2 <= this.categories.values().stream().filter(value -> value == category).count()) {
            throw new IllegalArgumentException("같은 카테고리는 최대 두 번 선택될 수 없습니다");
        }
        categories.put(dayOfWeek, category);
    }

    public void setDailyMenu(Coach coach, DayOfWeek dayOfWeek, Menu menu) {
        Map<DayOfWeek, Menu> dayOfWeekMenu = dailyMenu.computeIfAbsent(coach, k -> new HashMap<>());
        if (dayOfWeekMenu.containsKey(dayOfWeek)) {
            throw new IllegalStateException("이미 설정된 요일입니다");
        }
        if (dislikeMenus.containsMenu(menu)) {
            throw new IllegalArgumentException("설정할 수 없는 메뉴입니다");
        }
        if (dayOfWeekMenu.values().stream().anyMatch(value -> value.equals(menu))) {
            throw new IllegalArgumentException("이미 해당 코치에게 설정된 메뉴입니다");
        }
        if (!this.categories.containsKey(dayOfWeek)) {
            throw new IllegalStateException("카테고리가 설정되지 않은 요일입니다");
        }
        if (this.categories.get(dayOfWeek) != menu.getCategory()) {
            throw new IllegalStateException("설정된 카테고리와 다른 메뉴를 입력하였습니다");
        }

        dayOfWeekMenu.put(dayOfWeek, menu);
    }

    public int getCategorySize() {
        return categories.size();
    }

    public Map<DayOfWeek, Menu> getDailyMenu(Coach coach) {
        if (!dailyMenu.containsKey(coach)) {
            throw new IllegalStateException("없는 코치 입니다");
        }
        if (dailyMenu.get(coach).size() != 5) {
            throw new IllegalStateException("모든 날에 대한 메뉴가 추가되지 않았습니다");
        }

        return Map.copyOf(dailyMenu.get(coach));
    }
}
