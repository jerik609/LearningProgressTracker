package tracker.data.platform;

import tracker.data.platform.Account;
import tracker.data.student.EmailAddress;
import tracker.data.student.Student;

import java.security.InvalidParameterException;
import java.util.*;

public class Platform {
    private int nextCourseId = 0;
    private int addedStudents = 0;

    private final Set<EmailAddress> knownEmails = new HashSet<>();
    private final Map<Integer, String> knownCourses = new LinkedHashMap<>();
    private final Map<Integer, Account> accounts = new LinkedHashMap<>();

    public void addCourse(String courseId) {
        knownCourses.put(nextCourseId, courseId);
        nextCourseId++;
    }

    public boolean createAccount(Student student) {
        if (knownEmails.contains(student.getEmailAddress())) {
            System.out.println("This email is already taken.");
            return false;
        }
        final var account = new Account.Builder().student(student).courses(knownCourses.keySet()).build();
        accounts.putIfAbsent(account.getId(), account);
        knownEmails.add(student.getEmailAddress());
        addedStudents++;
        return true;
    }

    public Set<Integer> getAccountIds() {
        return accounts.keySet();
    }

    public Optional<Account> getAccount(Integer id) {
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
}
