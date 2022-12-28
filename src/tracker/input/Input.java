package tracker.input;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Input {
    public final static String INVALID_HYPHEN_APOSTROPHE_REGEX = "(?!.*[-']{2})(?!^[-'])(?!.*[-']$).*";
    public final static String INPUT_SPLIT_PATTERN_REGEX = " ";
    public final static String SUB_NAME_SPLIT_PATTERN_REGEX = "[-']";
    public final static String VALID_SUB_NAME_REGEX = "[A-Z][a-z]*";
    public final static String VALID_EMAIL_REGEX = "(\\w+\\.)*\\w+@\\w+\\.[a-z]{3}";

    public static boolean validateEmail(String input) {
        var pattern = Pattern.compile(VALID_EMAIL_REGEX);
        return pattern.matcher(input).matches();
    }

    public static boolean validateName(List<String> split) {
        var pattern = Pattern.compile(VALID_SUB_NAME_REGEX);
        return IntStream.rangeClosed(0, split.size() - 1)
                .mapToObj(split::get)
                .filter(item -> {
                    // split using ' and -, the sub names must be of format [A-Z][a-z]+
                    return !Arrays.stream(item.split(SUB_NAME_SPLIT_PATTERN_REGEX))
                            .allMatch(subItem -> {
                                System.out.println("SubItem: " + subItem);
                                return pattern.matcher(subItem).matches();
                            });
                })
                .peek(invalid -> System.out.println("Invalid name: " + invalid))
                .count() == 0;
    }

    public static void parseStudentStr(String input) {

        final var globalPattern = Pattern.compile(INVALID_HYPHEN_APOSTROPHE_REGEX);
        if (!globalPattern.matcher(input).matches()) {
            throw new InvalidParameterException("Invalid hyphen/apostrophe sequence: " + input);
        }

        var split = Arrays.stream(input.split(INPUT_SPLIT_PATTERN_REGEX)).toList();

        if (split.size() < 3) {
            throw new InvalidParameterException("Input cannot be split into <firstname> <surname> <email address>: " + input);
        }



        // validate parts


        validateName(split.subList(0, split.size() - 2));
        validateEmail(split.get(split.size() - 1));

        final var firstname = split.get(0).trim();
        final var surname = split.subList(0, split.size() - 2)
                .stream()
                .reduce("", (s, s2) -> s + " " + s2)
                .trim();
        final var emailAddress = split.get(split.size() - 1).trim();
        System.out.println(firstname + "; " + surname + "; " + emailAddress);
    }
}
