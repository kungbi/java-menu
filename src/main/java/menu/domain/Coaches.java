package menu.domain;

import java.util.List;

public class Coaches {
    private final List<Coach> coaches;

    public Coaches(List<Coach> coaches) {
        if (coaches.stream().map(Coach::getName).distinct().count() != coaches.size()) {
            throw new IllegalArgumentException("이름이 중복될 수 없습니다.");
        }
        if (!(2 <= coaches.size() && coaches.size() <= 5)) {
            throw new IllegalArgumentException("두 명에서 최대 5명까지 입력 가능합니다.");
        }
        this.coaches = coaches;
    }


    public int getSize() {
        return coaches.size();
    }

}
