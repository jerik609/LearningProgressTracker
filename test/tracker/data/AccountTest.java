package tracker.data;

import org.junit.jupiter.api.Test;

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
        final var account = new Account.Builder().student(student).courses(Set.of("aaa", "bbb")).build();

        assertEquals("John", account.getStudent().getName().getFirstname());
        assertEquals("Doe", account.getStudent().getName().getSurname());
        assertEquals("michal@michal.mm", account.getStudent().getEmailAddress().toString());
        assertEquals(Set.of("aaa", "bbb"), account.getCourses().keySet());
        assertEquals(Account.getLastAccountId(), account.getId());
    }
}
