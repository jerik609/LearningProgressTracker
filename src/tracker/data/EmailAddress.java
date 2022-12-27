package tracker.data;

public class EmailAddress {
    private final static String VALIDATION_REGEX = "";
    private final static Validator validator = new Validator(VALIDATION_REGEX);

    private final String emailAddress;

    private EmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public static EmailAddress buildFrom(String emailAddress) {
        if (validator.validate(emailAddress)) {
            return new EmailAddress(emailAddress);
        } else {
            return null;
        }
    }
}
