package tracker.data;

import java.util.regex.Pattern;

public class Validator {
    private final Pattern pattern;

    public Validator(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    boolean validate(String name) {
        final var matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
