package tracker.data.platform;

import tracker.data.student.EmailAddress;
import tracker.data.student.PointsInput;
import tracker.data.student.Student;

import java.util.*;
import java.util.stream.Collectors;

public class Platform {
    private int nextCourseId = 0;
    private int addedStudents = 0;

    private final Set<EmailAddress> knownEmails = new HashSet<>();
    private final Map<Integer, Course> knownCourses = new LinkedHashMap<>();
    private final Map<String, Account> accounts = new LinkedHashMap<>();

    public void addCourse(String courseName, int requiredPoints) {
        final var course = new Course(courseName, requiredPoints);
        knownCourses.put(nextCourseId, course);
        nextCourseId++;
    }

    public boolean addPoints(PointsInput points) {
        if (points.points().length != knownCourses.size()) {
            return false;
        }
        final var account = accounts.get(points.studentId());
        if (account == null) {
            return false;
        }
        for (int i = 0; i < points.points().length; ++i) {
            account.addPoints(i, points.points()[i]);
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

    public Set<Integer> getCourseIdsForCourseName(String courseName) {
        return knownCourses.entrySet().stream()
                .filter(entry -> entry.getValue().courseName().equals(courseName))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
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

    //TODO: make internal
    public Map<Integer, Integer> getEnrolledStudentsPerCourse() {
        // for all student accounts
        return accounts.entrySet().stream()
                // stream their "course score tracking" entities (= their number is equal to number of courses)
                .flatMap(account -> account.getValue().getCourses().entrySet().stream())
                // filter only those entities, which have points (= student is enrolled to that course)
                .filter(accountCourse -> accountCourse.getValue().getTotalPoints() > 0)
                // count number of non-zero entries (=student is enrolled) and store under the course id (= key)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        initial -> 1,
                        (prev, newVal) -> prev + 1));
    }

    public List<Map.Entry<Integer, Integer>> getCoursesOrderedByNumberOfEnrolledStudents() {
        return getEnrolledStudentsPerCourse().entrySet().stream().sorted(
                (o1, o2) -> o1.getValue().equals(o2.getValue()) ? 0 : o1.getValue() < o2.getValue() ? 1 : -1
        ).toList();
    }

    //TODO: make internal
    public Map<Integer, Long> getTasksPerCourse() {
        // for all student accounts
        return accounts.entrySet().stream()
                // stream their "course score tracking" entities (= their number is equal to number of courses)
                .flatMap(account -> account.getValue().getCourses().entrySet().stream())
                // sum up total number of tasks and store them under the course id (= key)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        initial -> initial.getValue().getTotalTasks(),
                        Long::sum));
    }

    public List<Map.Entry<Integer, Long>> getCoursesOrderedByNumberOfTasks() {
        return getTasksPerCourse().entrySet().stream().sorted(
                (o1, o2) -> o1.getValue().equals(o2.getValue()) ? 0 : o1.getValue() < o2.getValue() ? 1 : -1
        ).toList();
    }
}
