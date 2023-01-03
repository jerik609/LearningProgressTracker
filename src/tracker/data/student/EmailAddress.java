package tracker.data.student;

import java.util.Objects;
import java.util.regex.Pattern;

/*
check whether the provided string looks like an email address.
It should contain
- the name part,
- the @ symbol, and
- the domain part.
 */
public class EmailAddress {
    public final static String VALID_EMAIL_REGEX = "[\\w.]+@\\w+\\.\\w+";

    private final String emailAddress;

    private EmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
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
        System.out.println("Incorrect email.");
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!this.getClass().isInstance(obj)) {
            return false;
        }
        var email = this.getClass().cast(obj);
        return this.emailAddress.equals(email.getEmailAddress());
    }
}
