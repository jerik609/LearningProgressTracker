package tracker.data;

public class Name {
    private final static String VALIDATION_REGEX = "";
    private final static Validator validator = new Validator(VALIDATION_REGEX);

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return name;
    }

    public static Name buildFrom(String name) {
        if (validator.validate(name)) {
            return new Name(name);
        } else {
            return null;
        }
    }
}
