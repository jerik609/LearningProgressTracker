package tracker.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmailAddressTest {
    @Test
    @DisplayName("Email address is correctly created from valid address and valid owner name")
    public void validEmailAddress() {
        final var emailAddressStr = "jerik.dog@dogmail.dog";
        final var emailAddress = EmailAddress.buildFrom(emailAddressStr);
        assertNotNull(emailAddress);
        assertEquals(emailAddressStr, emailAddress.toString());
    }

    @Test
    public void emailTest() {
        final var testStr = "jerik.the.dogg@doge.wuf";
        assertTrue(EmailAddress.validateEmail(testStr));

        final var badTestStr = "jerik.the.dogg@@doge.wuf";
        assertFalse(EmailAddress.validateEmail(badTestStr));
    }
}
