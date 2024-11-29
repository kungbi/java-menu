package menu.domain;

import java.util.Arrays;
import java.util.List;

public enum DayOfWeek {
    MONDAY(0, "월요일"),
    TUESDAY(1, "화요일"),
    WEDNESDAY(2, "수요일"),
    THURSDAY(3, "목요일"),
    FRIDAY(4, "금요일");

    private final int order;
    private final String name;


    DayOfWeek(int order, String name) {
        this.order = order;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<DayOfWeek> getDaysOfWeek() {
        return Arrays.stream(DayOfWeek.values()).sorted().toList();
    }
}
