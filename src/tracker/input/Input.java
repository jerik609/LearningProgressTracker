package tracker.input;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.IntStream;

public class Input {

    public static void parseStudentStr(String input) {
        var split = input.split(" ");
        if (split.length < 3) {
            throw new InvalidParameterException("Input not in format <firstname> <surname> <email address>: " + input);
        }
        final var firstname = split[0];
        final var surname = IntStream.rangeClosed(1, split.length - 2)
                .mapToObj(i -> split[i])
                .reduce("", (s, s2) -> s + " " + s2);
        final var emailAddress = split[split.length - 1];
        System.out.println(firstname + "; " + surname + "; " + emailAddress);
    }
}
