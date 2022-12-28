package tracker.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/*
check whether the provided string looks like an email address.
It should contain
- the name part,
- the @ symbol, and
- the domain part.
 */
public class EmailAddress {
    public final static String VALID_EMAIL_REGEX = "(\\w+\\.)*\\w+@\\w+\\.[a-z]{3}";

    private final String emailAddress;

    private EmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return emailAddress;
    }

    public static boolean validateEmail(String input) {
        var pattern = Pattern.compile(VALID_EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(input).matches();
    }

    public static EmailAddress buildFrom(String emailAddress) {
        if (validateEmail(emailAddress)) {
            return new EmailAddress(emailAddress);
        }
        System.out.println("Invalid email address: " + emailAddress);
        return null;
    }
}
