package tracker.data.platform;

import org.junit.jupiter.api.Test;
import tracker.data.platform.Account;
import tracker.data.student.EmailAddress;
import tracker.data.student.Name;
import tracker.data.student.Student;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    public void createAccount() {
        final var student = new Student.Builder()
                .name(Name.buildFrom(List.of("John", "Doe")))
                .emailAddress(EmailAddress.buildFrom("michal@michal.mm"))
                .build();
        final var account = new Account.Builder().student(student).courses(Set.of(0, 1)).build();

        assertEquals("John", account.getStudent().getName().getFirstname());
        assertEquals("Doe", account.getStudent().getName().getSurname());
        assertEquals("michal@michal.mm", account.getStudent().getEmailAddress().toString());
        assertEquals(Set.of(0, 1), account.getCourses().keySet());
        assertEquals(Account.getLastAccountId(), account.getId());
    }
}
