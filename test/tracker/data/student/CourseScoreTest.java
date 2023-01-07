package tracker.data.student;

import org.junit.jupiter.api.Test;
import tracker.data.student.CourseScore;

import static org.junit.jupiter.api.Assertions.*;

class CourseScoreTest {
    @Test
    public void createCourse() {
        final var course = new CourseScore(13);
        course.addPoints(10);
        course.addPoints(5);
        course.addPoints(1);
        course.addPoints(7);
        assertEquals(13, course.getId());
        assertEquals(23, course.getTotalPoints());
    }
}
