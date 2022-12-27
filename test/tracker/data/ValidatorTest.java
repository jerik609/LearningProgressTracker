package tracker.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @Test
    @DisplayName("must contain: contains the value several times")
    public void validatorMustContainPositive() {
        final var testStr = "there are several values of the value value";
        final var matchStr = ".*";
        final var mustContain = "value";
        final var validator = new Validator(matchStr);
        assertTrue(validator.validate(testStr, mustContain));
    }

    @Test
    @DisplayName("must contain: does not contain the must contain value")
    public void validatorMustContainNegative() {
        final var testStr = "does not contain the v4lu3";
        final var matchStr = ".*";
        final var mustContain = "value";
        final var validator = new Validator(matchStr);
        assertFalse(validator.validate(testStr, mustContain));
    }
}
