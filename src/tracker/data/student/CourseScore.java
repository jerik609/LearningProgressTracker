package tracker.data.student;

import java.util.ArrayList;
import java.util.List;

public class CourseScore {

    private final Integer id;
    private final List<Integer> points = new ArrayList<>();

    public CourseScore(Integer id) {
        this.id = id;
    }

    public void addPoints(int i) {
        points.add(i);
    }

    public Integer getId() {
        return id;
    }

    public int getTotalPoints() {
        return points.stream().mapToInt(Integer::intValue).sum();
    }
}
