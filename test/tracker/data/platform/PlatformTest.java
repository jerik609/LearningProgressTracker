package tracker.data.platform;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static tracker.data.platform.PlatformTestUtils.addPoints;
import static tracker.data.platform.PlatformTestUtils.createAccount;

class PlatformTest {
    private Platform platform;

    @BeforeEach
    void init() {
        platform = new Platform();
        platform.addCourse("Cooking", 100);
        platform.addCourse("Baking", 150);
    }

    @Test
    void createPlatformTest() {
        final var acct1 = createAccount(platform, "Wile", "Coyote", "wile.coyote@acme.com");
        final var acct2 = createAccount(platform, "Road", "Runner", "fasterthanlight@acme.com");

        assertEquals(2, platform.getAccountIds().size());

        addPoints(platform, acct1, 0, 0);
        addPoints(platform, acct2, 1, 0);

        assertEquals(Set.of(0, 1), platform.getAccount(acct1).get().getCourses().keySet());
        assertEquals("Wile", platform.getAccount(acct1).get().getStudent().getName().getFirstname());
        assertEquals("Coyote", platform.getAccount(acct1).get().getStudent().getName().getSurname());
        assertEquals("wile.coyote@acme.com", platform.getAccount(acct1).get().getStudent().getEmailAddress().getEmailAddress());
    }

    @Test
    void getCoursesByStringTest() {
        platform.addCourse("Swimming", 100);
        platform.addCourse("Eating", 110);
        platform.addCourse("Swimming", 120);
        assertEquals(2, platform.getCourseIdsForCourseName("Swimming").size());
    }

    @Test
    void getScoreTest() {
        final var acct1 = createAccount(platform, "Wile", "Coyote", "wile.coyote@acme.com");
        addPoints(platform, acct1, 5, 7);
        assertEquals(acct1 + " points: Cooking=5; Baking=7", platform.getAccountDetails(Account.getLastAccountId()));
    }
}
