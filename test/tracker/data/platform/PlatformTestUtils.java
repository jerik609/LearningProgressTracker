package tracker.data.platform;

import tracker.data.student.EmailAddress;
import tracker.data.student.Name;
import tracker.data.student.PointsInput;
import tracker.data.student.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlatformTestUtils {
    /**
     * Create account for provided credentials.
     * A non-prod method: no checks are performed, caller must check the result.
     * @param platform
     * @param firstname
     * @param surname
     * @param email
     * @return
     */
    public static String createAccount(Platform platform, String firstname, String surname, String email) {
        final var student = new Student.Builder()
                .name(Name.buildFrom(List.of(firstname, surname)))
                .emailAddress(EmailAddress.buildFrom(email))
                .build();
        final var id = platform.createAccount(student);
        assertFalse(id.isEmpty());
        return id;
    }

    /**
     * Add points to provided account.
     * A non-prod method: no checks are performed, caller must check the result.
     * @param platform
     * @param accountId
     * @param points
     */
    public static void addPoints(Platform platform, String accountId, int... points) {
        platform.addPoints(new PointsInput(accountId, points));
    }
}
