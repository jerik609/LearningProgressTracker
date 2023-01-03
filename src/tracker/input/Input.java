package tracker.input;

import tracker.data.student.EmailAddress;
import tracker.data.student.Name;
import tracker.data.student.Student;

import java.util.Arrays;

public class Input {
    public final static String INPUT_SPLIT_PATTERN_REGEX = " ";

    public static Student parseStudentStr(String input) {
        final var split = Arrays.stream(input.split(INPUT_SPLIT_PATTERN_REGEX)).toList();
        if (split.size() < 3) {
            System.out.println("Incorrect credentials.");
            return null;
        }

        final var name = Name.buildFrom(split.subList(0, split.size() - 1));
        if (name == null) {
            //System.out.println("Incorrect credentials.");
            return null;
        }

        final var emailAddress = EmailAddress.buildFrom(split.get(split.size() - 1));

        return new Student.Builder().name(name).emailAddress(emailAddress).build();
    }
}
