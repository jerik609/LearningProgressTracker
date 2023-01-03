package tracker.data.platform;

import org.junit.jupiter.api.Test;
import tracker.data.platform.Account;
import tracker.data.platform.Platform;
import tracker.data.student.EmailAddress;
import tracker.data.student.Name;
import tracker.data.student.Student;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {

    @Test
    void createPlatformTest() {
        var platform = new Platform();
        platform.addCourse("Cooking");
        platform.addCourse("Baking");
        var student = new Student.Builder()
                .name(Name.buildFrom(List.of("Wile", "Coyote")))
                .emailAddress(EmailAddress.buildFrom("test@test.tst"))
                .build();
        platform.createAccount(student);

        var accountIds = platform.getAccountIds();

        assertEquals(1, accountIds.size());
        var account = platform.getAccount(accountIds.stream().findFirst().orElseThrow()).orElseThrow();

        assertEquals(Account.getLastAccountId(), account.getId());
        assertEquals(Set.of("Cooking", "Baking"), account.getCourses().keySet());
        assertEquals("Wile", account.getStudent().getName().getFirstname());
        assertEquals("Coyote", account.getStudent().getName().getSurname());
        assertEquals("test@test.tst", account.getStudent().getEmailAddress().getEmailAddress());
    }

    @Test
    void createAccount() {
    }
}