package tracker.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Account {
    private static int lastAccountId = 10000;

    private final int id;
    private final Student student;
    private final Map<String, CourseScore> courses = new HashMap<>();

    private Account(Student student, Set<String> courses) {
        this.id = ++lastAccountId;
        this.student = student;
        for (var course : courses) {
            var courseScore = new CourseScore(course);
            this.courses.put(courseScore.getName(), courseScore);
        }
    }

    public static int getLastAccountId() {
        return lastAccountId;
    }

    public int getId() {
        return id;
    }

    public Map<String, CourseScore> getCourses() {
        return courses;
    }

    public Student getStudent() {
        return student;
    }

    public static class Builder {

        private Student student;
        private Set<String> courses;

        public Builder student(Student student) {
            this.student = student;
            return this;
        }

        public Builder courses(Set<String> courses) {
            this.courses = courses;
            return this;
        }

        public Account build() {
            return new Account(student, courses);
        }
    }
}
