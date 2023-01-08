package tracker.data.platform;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tracker.data.platform.PlatformTestUtils.addPoints;
import static tracker.data.platform.PlatformTestUtils.createAccount;

public class StatisticsTest {

    private Platform platform;
    private List<String> userIds;

    @BeforeEach
    void init() {
        platform = new Platform();
        platform.addCourse("Cooking", 50);
        platform.addCourse("Baking", 55);
        platform.addCourse("MixingCocktails", 45);
        platform.addCourse("Frying", 90);

        userIds = new ArrayList<>();
        userIds.add(createAccount(platform, "Wile", "Coyote", "wile.coyote@acme.com"));
        userIds.add(createAccount(platform, "Road", "Runner", "fasterthanlight@ace.com"));
        userIds.add(createAccount(platform, "John", "Doe", "john.doe@gmail.com"));
        userIds.add(createAccount(platform, "Jane", "Doe", "jane.doe@gmail.com"));

        assertEquals(4, userIds.size());

        addPoints(platform, userIds.get(0), 1, 1, 0, 0);
        addPoints(platform, userIds.get(0), 8, 6, 0, 0);

        addPoints(platform, userIds.get(1), 0, 4, 6, 0);
        addPoints(platform, userIds.get(1), 0, 3, 4, 0);

        addPoints(platform, userIds.get(2), 9, 9, 9, 0);
        addPoints(platform, userIds.get(2), 5, 4, 3, 0);

        addPoints(platform, userIds.get(3), 4, 5, 6, 7);
        addPoints(platform, userIds.get(3), 3, 1, 3, 7);
    }

    // Most popular = ordering by number of enrolled students (a student is enrolled, if he has any score in that course)
    // Least popular = as most popular, but reversed
    // Highest activity = ordering by number of completed tasks (number of tasks submissions)
    // Lowest activity = as highest activity, but reversed
    // Easiest course = ordering by highest average grade per assignment
    // Hardest course = as easiest course, but reversed

    @Test
    void getMostPopularCourse() {
        System.out.println(platform.getEnrolledStudentsPerCourse());
        System.out.println(platform.getCoursesOrderedByNumberOfEnrolledStudents());
    }

    @Test
    void getHighestActivityCourse() {
        System.out.println(platform.getTasksPerCourse());
        System.out.println(platform.getCoursesOrderedByNumberOfTasks());
    }
}