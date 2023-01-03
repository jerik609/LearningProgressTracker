package tracker.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailAddressTest {
    @Test
    @DisplayName("Email address is correctly created from valid address and valid owner name")
    public void buildEmailAddressFromValidEmail() {
        final var emailAddressStr = "jerik.dog@dogmail.dog";
        final var emailAddress = EmailAddress.buildFrom(emailAddressStr);
        assertNotNull(emailAddress);
        assertEquals(emailAddressStr, emailAddress.toString());
    }

    @Test
    public void validEmailTest() {
        final var testStr = "jerik.the.dogg@doge.wuf";
        assertTrue(EmailAddress.validateEmail(testStr));
    }

    @Test
    public void invalidEmailTest() {
        final var badTestStr = "jerik.the.dogg@@doge.wuf";
        assertFalse(EmailAddress.validateEmail(badTestStr));
    }

    @Test
    public void equalityTest() {
        final var email1 = EmailAddress.buildFrom("jerik.the.dogg@doge.wuf");
        assertEquals(email1, email1);

        final var email2 = EmailAddress.buildFrom("jerik.the.dogg@doge.wuf");
        assertEquals(email2, email2);

        assertEquals(email1, email2);
        assertEquals(email2, email1);
    }

    @Test
    public void hashCodeTest() {
        final var email1 = EmailAddress.buildFrom("jerik.the.dogg@doge.wuf");
        final var email2 = EmailAddress.buildFrom("jerik.the.dogg@doge.wuf");
        assert email1 != null;
        assert email2 != null;
        assertEquals(email1.hashCode(), email2.hashCode());
    }
}
