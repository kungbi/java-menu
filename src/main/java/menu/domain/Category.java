package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;

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

    public int getOrder() {
        return order;
    }

    public static Category getRandomCategory() {
        int pickedNumber = Randoms.pickNumberInRange(1, 5);
        for (Category category : Category.values()) {
            if (category.getOrder() == pickedNumber) {
                return category;
            }
        }
        throw new IllegalStateException("존재하지 않는 카테고리 입니다.");
    }
}
