package tracker.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmailAddressTest {

    @Test
    @DisplayName("Email address is correctly created from valid address and valid owner name")
    public void validEmailAddress() {
        final var emailAddressStr = "jerik.dog@dogmail.dog";
        final var ownerName = "Jerik";
        final var emailAddress = EmailAddress.buildFrom(emailAddressStr, ownerName);
        assertNotNull(emailAddress);
        assertEquals(emailAddressStr, emailAddress.toString());
    }

}
