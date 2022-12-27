package tracker.input;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    @Test
    public void test() {
        final var testStr = "Jerik The Big Bad-Ass Dog jerik.the.dogg@doge.wuf";
        Input.parseStudentStr(testStr);
    }
}