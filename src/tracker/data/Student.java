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
        private Name name;
        private EmailAddress emailAddress;

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Builder emailAddress(EmailAddress emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Student build() {
            if (name == null || emailAddress == null) {
                return null;
            }
            return new Student(name, emailAddress);
        }
    }
}
