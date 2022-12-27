package tracker.data;

public class Name {
    private final static String VALIDATION_REGEX = ".*";
    private final static Validator validator = new Validator(VALIDATION_REGEX);

    private final String firstname;
    private final String surname;

    private Name(String firstname, String surname) {
        this.firstname = firstname;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return firstname + " " + surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public static Name buildFrom(String firstname, String surname) {
        if (validator.validate(firstname) && validator.validate(surname)) {
            return new Name(firstname, surname);
        } else {
            System.out.println("Invalid name: " + firstname + " " + surname);
            return null;
        }
    }
}
