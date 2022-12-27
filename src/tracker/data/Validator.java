package tracker.data;

import java.util.regex.Pattern;

public class Validator {
    private final Pattern pattern;

    public Validator(String regex) {
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    boolean validate(String name, String... mustContain) {
        final var matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
