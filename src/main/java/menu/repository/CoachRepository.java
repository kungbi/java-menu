package menu.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import menu.domain.Coach;

public class CoachRepository implements Repository<Coach> {
    private final List<Coach> coaches = new ArrayList<>();

    @Override
    public void add(Coach coach) {
        if (this.exists(coach.getName())) {
            throw new IllegalArgumentException("코치의 이름은 중복될 수 없습니다.");
        }
        coaches.add(coach);
    }

    public int getSize() {
        return coaches.size();
    }

    public Optional<Coach> findByName(String name) {
        return coaches.stream().filter(c -> c.getName().equals(name)).findFirst();
    }

    public boolean exists(String name) {
        return this.findByName(name).isPresent();
    }

    public boolean isMealAvailable() {
        if (!(2 <= this.getSize() && this.getSize() <= 5)) {
            return false;
        }
        return true;
    }
}
