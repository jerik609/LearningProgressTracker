package tracker.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseScoreTest {
    @Test
    public void createCourse() {
        final var course = new CourseScore("Java");
        course.addPoints(10);
        course.addPoints(5);
        course.addPoints(1);
        course.addPoints(7);
        assertEquals("Java", course.getName());
        assertEquals(23, course.getTotalPoints());
    }
}
