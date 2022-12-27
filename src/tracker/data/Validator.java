package tracker.data;

import java.util.regex.Pattern;

public class Validator {
    private final static int MATCH_MODE = Pattern.CASE_INSENSITIVE;
    private final Pattern pattern;

    public Validator(String regex) {
        this.pattern = Pattern.compile(regex, MATCH_MODE);
    }

    boolean validate(String name, String... mustContain) {
        var valid = true;
        for (var item : mustContain) {
            final var matcher = Pattern.compile(item, MATCH_MODE).matcher(name);
            var count = 0;
            while (matcher.find()) {
                ++count;
            }
            valid &= count > 0;
        }
        valid &= pattern.matcher(name).matches();
        return valid;
    }
}
