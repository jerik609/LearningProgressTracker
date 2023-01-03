package tracker.data.student;

import java.util.ArrayList;
import java.util.List;

public class CourseScore {

    private final String name;
    private final List<Integer> points = new ArrayList<>();

    public CourseScore(String name) {
        this.name = name;
    }

    public void addPoints(int i) {
        points.add(i);
    }

    public String getName() {
        return name;
    }

    public int getTotalPoints() {
        return points.stream().mapToInt(Integer::intValue).sum();
    }
}
