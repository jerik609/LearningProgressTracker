package tracker.input;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tracker.data.platform.Platform;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {
    @Test
    public void validInputTest() {
        final var testStr = "Jerik The Big Bad-Ass Dog jerik.the.dogg@doge.wuf";
        assertNotNull(Input.parseStudentStr(testStr));
    }

    @Test
    public void invalidInputTest() {
        final var testStr = "John D. name@domain.com";
        assertEquals(Optional.empty(), Input.parseStudentStr(testStr));
    }

    @Test
    public void validStudent() {
        final var ok = "Mary Emelianenko 125367at@zzz90.z9";
        assertNotNull(Input.parseStudentStr(ok));
    }

    @Test
    public void validPoints() {
        var platform = Mockito.mock(Platform.class);
        Mockito.when(platform.getCoursesIds()).thenReturn(Set.of(0, 1, 2, 3));

        var input = new Input(platform);
        var points = input.parseScore("10001 5 8 7 3");
        System.out.println(points);
    }
}
