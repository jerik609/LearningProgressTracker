package tracker.data.student;

import java.util.ArrayList;
import java.util.List;

public class CourseScore {

    private final Integer id;
    private boolean notified = false;
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

    public long getTotalPoints() {
        return points.stream().mapToInt(Integer::intValue).sum();
    }

    public long getTotalTasks() {
        return points.size();
    }

    public void setNotified() {
        notified = true;
    }

    public boolean isNotified() {
        return notified;
    }
}
