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
    private static int lastAccountId = 9999;

    private final String id;
    private final Student student;
    private final Map<Integer, CourseScore> courses = new HashMap<>();

    private Account(Student student, Set<Integer> courses) {
        this.id = String.valueOf(++lastAccountId);
        this.student = student;
        for (var course : courses) {
            var courseScore = new CourseScore(course);
            this.courses.put(courseScore.getId(), courseScore);
        }
    }

    public static String getLastAccountId() {
        return String.valueOf(lastAccountId);
    }

    public String getId() {
        return id;
    }

    public Map<Integer, CourseScore> getCourses() {
        return courses;
    }

    public Student getStudent() {
        return student;
    }

    public boolean addPoints(int courseId, int points) {
        if (points != 0) {
            getCourses().get(courseId).addPoints(points);
            return true;
        } else {
            return false;
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
