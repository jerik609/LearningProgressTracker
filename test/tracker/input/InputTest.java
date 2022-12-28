package tracker.input;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {
    @Test
    public void fullyIntegratedValidationTest() {
        final var testStr = "Jerik The Big Bad-Ass Dog jerik.the.dogg@doge.wuf";
        Input.parseStudentStr(testStr);
    }
}