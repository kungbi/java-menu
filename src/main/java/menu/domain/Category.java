package menu.domain;

public enum Category {
    JAPANESE(1, "일식"),
    KOREAN(2, "한식"),
    CHINESE(3, "중식"),
    ASIAN(4, "아시안"),
    WESTERN(5, "양식");

    private final int order;
    private final String name;

    Category(int order, String name) {
        this.order = order;
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
