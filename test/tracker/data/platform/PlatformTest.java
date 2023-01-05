package tracker.data.platform;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.data.student.EmailAddress;
import tracker.data.student.Name;
import tracker.data.student.PointsInput;
import tracker.data.student.Student;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {

    private Platform platform;

    @BeforeEach
    void init() {
        platform = new Platform();
        platform.addCourse("Cooking");
        platform.addCourse("Baking");
        var student = new Student.Builder()
                .name(Name.buildFrom(List.of("Wile", "Coyote")))
                .emailAddress(EmailAddress.buildFrom("test@test.tst"))
                .build();
        platform.createAccount(student);

        var account = platform.getAccount(Account.getLastAccountId())
                .orElseThrow(() -> new RuntimeException("Could not create account!"));

        final var pointsInput = new PointsInput(account.getId(), new int[]{5, 7});
        account.addPoints(pointsInput);
    }

    @Test
    void createPlatformTest() {
        var accountIds = platform.getAccountIds();

        assertEquals(1, accountIds.size());
        var account = platform.getAccount(accountIds.stream().findFirst().orElseThrow()).orElseThrow();

        assertEquals(Account.getLastAccountId(), account.getId());
        assertEquals(Set.of(0, 1), account.getCourses().keySet());
        assertEquals("Wile", account.getStudent().getName().getFirstname());
        assertEquals("Coyote", account.getStudent().getName().getSurname());
        assertEquals("test@test.tst", account.getStudent().getEmailAddress().getEmailAddress());
    }

    @Test
    void getScore() {
        assertEquals(Account.getLastAccountId() + " points: Cooking=5; Baking=7", platform.getAccountDetails(Account.getLastAccountId()));
    }
}