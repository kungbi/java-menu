package menu.domain;

public class Coach {
    private final String name;

    public Coach(String name) {
        if (!(2 <= name.length() && name.length() <= 4)) {
            throw new IllegalArgumentException("코치의 이름은 최소 2글자, 최대 4글자 입니다.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
