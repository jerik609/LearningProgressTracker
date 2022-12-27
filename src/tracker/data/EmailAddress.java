package tracker.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
check whether the provided string looks like an email address.
It should contain
- the name part,
- the @ symbol, and
- the domain part.
 */
public class EmailAddress {
    private final List<String> LOOKAHEAD_VALIDATION_REGEX_LIST = Collections.emptyList();
    private final static String VALIDATION_REGEX = "(\\w+\\.)*\\w+@\\w+\\.[a-z]{3}";
    private final static Validator validator = new Validator(VALIDATION_REGEX);

    private final String emailAddress;

    private EmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return emailAddress;
    }

    public static EmailAddress buildFrom(String emailAddress, Name owner) {
        if (validator.validate(emailAddress, new String[]{owner.getFirstname()})) {
            return new EmailAddress(emailAddress);
        } else {
            System.out.println("Invalid email address: " + emailAddress);
            return null;
        }
    }
}
