package menu.dtos;

import java.util.Map;
import menu.domain.Category;
import menu.domain.Coach;
import menu.domain.DayOfWeek;
import menu.domain.Menu;

public record RecommendedMenus(Map<DayOfWeek, Category> categories, Map<Coach, Map<DayOfWeek, Menu>> recommendedMenus) {
}
