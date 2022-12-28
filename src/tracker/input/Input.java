package tracker.input;

import tracker.data.EmailAddress;
import tracker.data.Name;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Input {
    public final static String INPUT_SPLIT_PATTERN_REGEX = " ";

    public static void parseStudentStr(String input) {
        final var split = Arrays.stream(input.split(INPUT_SPLIT_PATTERN_REGEX)).toList();
        if (split.size() < 3) {
            throw new InvalidParameterException("Input cannot be split into <firstname> <surname> <email address>: " + input);
        }

        final var name = Name.buildFrom(split.subList(0, split.size() - 2));
        if (name == null) {
            System.out.println("Invalid input: " + input);
            return;
        }

        final var emailAddress = EmailAddress.buildFrom(split.get(split.size() - 1));

        System.out.println(name.getFirstname() + "; " + name.getSurname() + "; " + emailAddress);
    }
}
