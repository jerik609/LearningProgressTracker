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

    @Test
    public void nameTest() {
        final List<String> nameStr = List.of("Jean-Claude", "O'Neill");
        assertTrue(Input.validateName(nameStr));
    }

    @Test
    public void emailTest() {
        final var testStr = "jerik.the.dogg@doge.wuf";
        assertTrue(Input.validateEmail(testStr));

        final var badTestStr = "jerik.the.dogg@@doge.wuf";
        assertFalse(Input.validateEmail(badTestStr));
    }

    @Test
    public void hyphenApostropheValidationTest() {
        final String[] testStrs = new String[]{
                "in--between", "in''between",
                "in-'between", "in'-between",
                "bordering'", "bordering-",
                "-bordering", "'bordering"
        };
        final var pattern = Pattern.compile(Input.INVALID_HYPHEN_APOSTROPHE_REGEX);
        for (var item : testStrs) {
            System.out.println("processing: " + item);
            assertFalse(pattern.matcher(item).matches());
        }
    }

}