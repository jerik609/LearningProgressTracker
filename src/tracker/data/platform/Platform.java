package tracker.data.platform;

import tracker.data.platform.Account;
import tracker.data.student.EmailAddress;
import tracker.data.student.PointsInput;
import tracker.data.student.Student;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

public class Platform {
    private int nextCourseId = 0;
    private int addedStudents = 0;

    private final Set<EmailAddress> knownEmails = new HashSet<>();
    private final Map<Integer, String> knownCourses = new LinkedHashMap<>();
    private final Map<String, Account> accounts = new LinkedHashMap<>();

    public void addCourse(String courseId) {
        knownCourses.put(nextCourseId, courseId);
        nextCourseId++;
    }

    public boolean addPoints(PointsInput points) {
        if (points.points().length != knownCourses.size()) {
            //throw new InvalidParameterException("points and courses sizes do not match");
            return false;
        }
        final var account = accounts.get(points.studentId());
        if (account == null) {
            return false;
        }
        for (int i = 0; i < points.points().length; ++i) {
            account.getCourses().get(i).addPoints(points.points()[i]);
        }
        return true;
    }

    public String createAccount(Student student) {
        if (knownEmails.contains(student.getEmailAddress())) {
            System.out.println("This email is already taken.");
            return "";
        }
        final var account = new Account.Builder().student(student).courses(knownCourses.keySet()).build();
        accounts.putIfAbsent(account.getId(), account);
        knownEmails.add(student.getEmailAddress());
        addedStudents++;
        return account.getId();
    }

    public Set<String> getAccountIds() {
        return accounts.keySet();
    }

    public Optional<Account> getAccount(String id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public Set<Integer> getCoursesIds() {
        return knownCourses.keySet();
    }

    public int getAddedStudents() {
        final var _addedStudents = addedStudents;
        addedStudents = 0;
        return _addedStudents;
    }

    public String getAccountDetails(String id) {
        final var account = accounts.get(id);
        if (account == null) {
            return "No student is found for id=" + id;
        } else {
            return id + " points: " + account.getCourses().entrySet().stream()
                    .map(course -> knownCourses.get(course.getKey()) + "=" + course.getValue().getTotalPoints())
                    .collect(Collectors.joining("; "));
        }
    }
}
