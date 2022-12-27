package tracker.data;

public class Student {
    private final Name name;
    private final EmailAddress emailAddress;

    private Student(Name name, EmailAddress emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public Name getName() {
        return name;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public static class Builder {
        private String name;
        private String emailAddress;

        Builder name(String name) {
            this.name = name;
            return this;
        }

        Builder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        Student build() {
            final var name = Name.buildFrom(this.name);
            final var emailAddress = EmailAddress.buildFrom(this.emailAddress);
            if (name == null || emailAddress == null) {
                return null;
            }
            return new Student(name, emailAddress);
        }
    }
}
