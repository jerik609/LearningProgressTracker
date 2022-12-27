package tracker.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailAddressValidatorTest {

    @Test
    public void test() {
        final var name = Name.buildFrom("Jerik Da'Dog");
        final var emailAddress = EmailAddress.buildFrom("jerik.dog@dogmail.dog");

        final var builder = new Student.Builder();
        final var student = builder.name(name).emailAddress(emailAddress).build();

        assertEquals(name, student.getName());
        assertEquals(emailAddress, student.getEmailAddress());
    }

}
