package tracker.data.platform;

import tracker.data.student.CourseScore;
import tracker.data.student.PointsInput;
import tracker.data.student.Student;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Account {
    private static int lastAccountId = 10000;

    private final int id;
    private final Student student;
    private final Map<Integer, CourseScore> courses = new HashMap<>();

    private Account(Student student, Set<Integer> courses) {
        this.id = ++lastAccountId;
        this.student = student;
        for (var course : courses) {
            var courseScore = new CourseScore(course);
            this.courses.put(courseScore.getId(), courseScore);
        }
    }

    public static int getLastAccountId() {
        return lastAccountId;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, CourseScore> getCourses() {
        return courses;
    }

    public Student getStudent() {
        return student;
    }

    public void addPoints(PointsInput points) {
        if (points.points().length != courses.size()) {
            throw new InvalidParameterException("points and courses sizes do not match");
        }
        for (int i = 0; i < points.points().length; ++i) {
            courses.get(i).addPoints(points.points()[i]);
        }
    }

    public static class Builder {

        private Student student;
        private Set<Integer> courses;

        public Builder student(Student student) {
            this.student = student;
            return this;
        }

        public Builder courses(Set<Integer> courses) {
            this.courses = courses;
            return this;
        }

        public Account build() {
            return new Account(student, courses);
        }
    }
}
