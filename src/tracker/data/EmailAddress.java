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

    public static EmailAddress buildFrom(String emailAddress, String owner) {
        if (validator.validate(emailAddress, owner)) {
            return new EmailAddress(emailAddress);
        } else {
            System.out.println("Invalid email address: " + emailAddress);
            return null;
        }
    }
}
