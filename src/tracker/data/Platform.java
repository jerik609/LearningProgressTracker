package tracker.data;

import java.security.InvalidParameterException;
import java.util.*;

public class Platform {

    private final Set<EmailAddress> knownEmails = new HashSet<>();
    private final Set<String> knownCourses = new HashSet<>();
    private final Map<Integer, Account> accounts = new HashMap<>();

    public void addCourse(String courseId) {
        knownCourses.add(courseId);
    }

    public void createAccount(Student student) {
        if (knownEmails.contains(student.getEmailAddress())) {
            throw new InvalidParameterException("email already registered: " + student.getEmailAddress().getEmailAddress());
        }
        var account = new Account.Builder().student(student).courses(knownCourses).build();
        accounts.putIfAbsent(account.getId(), account);
        knownEmails.add(student.getEmailAddress());
    }

    public Set<Integer> getAccountIds() {
        return accounts.keySet();
    }

    public Optional<Account> getAccount(Integer id) {
        return Optional.ofNullable(accounts.get(id));
    }
}
