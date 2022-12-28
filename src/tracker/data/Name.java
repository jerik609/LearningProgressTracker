package tracker.data;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Name {
    public final static String INVALID_HYPHEN_APOSTROPHE_REGEX = "(?!.*[-']{2})(?!^[-'])(?!.*[-']$).*";
    public final static String SUB_NAME_SPLIT_PATTERN_REGEX = "[-']";
    public final static String VALID_SUB_ITEM_REGEX = "[A-Z][a-z]*";

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

    public static boolean validateName(List<String> split) {
        final var hyphenApostrophePattern = Pattern.compile(INVALID_HYPHEN_APOSTROPHE_REGEX);
        final var subItemPattern = Pattern.compile(VALID_SUB_ITEM_REGEX);

        var valid = true;
        for (int i = 0; i < split.size(); i++) {
            valid = hyphenApostrophePattern.matcher(split.get(i)).matches();
            valid &= Arrays.stream(split.get(i)
                                    .split(SUB_NAME_SPLIT_PATTERN_REGEX))
                            .allMatch(subItem -> {
                                final var validSubItem = subItemPattern.matcher(subItem).matches();
                                //if (!validSubItem) System.out.println("Invalid subItem: " + subItem);
                                return validSubItem;
                            });
            if (!valid) {
                if (i == 0) {
                    System.out.println("Incorrect first name");
                } else {
                    System.out.println("Incorrect last name");
                }
                return false;
            }
        }
        return true;
    }

    public static Name buildFrom(List<String> split) {
        if (validateName(split)) {
            final var firstname = split.get(0).trim();
            final var surname = split.subList(1, split.size())
                    .stream()
                    .reduce("", (s, s2) -> s + " " + s2)
                    .trim();
            return new Name(firstname, surname);
        }
        //System.out.println("Invalid name: " + split);
        return null;
    }
}
