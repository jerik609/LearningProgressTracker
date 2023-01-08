package tracker.data.platform;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.data.student.EmailAddress;
import tracker.data.student.Name;
import tracker.data.student.PointsInput;
import tracker.data.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {
    private Platform platform;

    private String createAccount(String firstname, String surname, String email) {
        final var student = new Student.Builder()
                .name(Name.buildFrom(List.of(firstname, surname)))
                .emailAddress(EmailAddress.buildFrom(email))
                .build();
        final var id = platform.createAccount(student);
        assertFalse(id.isEmpty());
        return id;
    }

    private void addPoints(String accountId, int... points) {
        //final var account = platform.getAccount(accountId).orElseThrow();
        platform.addPoints(new PointsInput(accountId, points));
    }

    @BeforeEach
    void init() {
        platform = new Platform();
        platform.addCourse("Cooking");
        platform.addCourse("Baking");
    }

    @Test
    void createPlatformTest() {
        final var acct1 = createAccount("Wile", "Coyote", "wile.coyote@acme.com");
        final var acct2 = createAccount("Road", "Runner", "fasterthanlight@acme.com");

        assertEquals(2, platform.getAccountIds().size());

        addPoints(acct1, 0, 0);
        addPoints(acct2, 1, 0);

        assertEquals(Set.of(0, 1), platform.getAccount(acct1).get().getCourses().keySet());
        assertEquals("Wile", platform.getAccount(acct1).get().getStudent().getName().getFirstname());
        assertEquals("Coyote", platform.getAccount(acct1).get().getStudent().getName().getSurname());
        assertEquals("wile.coyote@acme.com", platform.getAccount(acct1).get().getStudent().getEmailAddress().getEmailAddress());
    }

    @Test
    void getCourseStats() {
        //var stats = platform.getStatistics(String course);
    }

    @Test
    void getCoursesByStringTest() {
        platform.addCourse("Swimming");
        platform.addCourse("Eating");
        platform.addCourse("Swimming");
        assertEquals(2, platform.getCourseIdsForCourseName("Swimming").size());
    }

    @Test
    void getMostPopularCourse() {
        platform.addCourse("Sleeping");

        final var acct1 = createAccount("Wile", "Coyote", "wile.coyote@acme.com");
        final var acct2 = createAccount("Road", "Runner", "fasterthanlight@acme.com");
        final var acct3 = createAccount("John", "Doe", "john.doe@gmail.com");

        //assertEquals(2, platform.getAccountIds().size());

        addPoints(acct1, 0, 0, 10);
        addPoints(acct2, 1, 4, 0);
        addPoints(acct3, 16, 0, 12);

        System.out.println(platform.totalCoursesScoreCount());
    }

    @Test
    void getScore() {
        final var acct1 = createAccount("Wile", "Coyote", "wile.coyote@acme.com");
        addPoints(acct1, 5, 7);
        assertEquals(acct1 + " points: Cooking=5; Baking=7", platform.getAccountDetails(Account.getLastAccountId()));
    }
}
