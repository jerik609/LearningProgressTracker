package tracker.input;

import tracker.data.platform.Platform;
import tracker.data.student.EmailAddress;
import tracker.data.student.Name;
import tracker.data.student.PointsInput;
import tracker.data.student.Student;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public class Input {
    public final static String INPUT_SPLIT_PATTERN_REGEX = " ";
    public final static String VALID_POINTS_INPUT_PATTERN = "\\w+( \\d+)* \\d+";

    private final Pattern validPointsPattern = Pattern.compile(VALID_POINTS_INPUT_PATTERN);

    //TODO: this is here to get the courses information - pass global config differently
    private final Platform platform;

    public Input(Platform platform) {
        this.platform = platform;
    }

    public static Optional<Student> parseStudentStr(String input) {
        final var split = Arrays.stream(input.split(INPUT_SPLIT_PATTERN_REGEX)).toList();
        if (split.size() < 3) {
            System.out.println("Incorrect credentials.");
            return Optional.empty();
        }

        final var name = Name.buildFrom(split.subList(0, split.size() - 1));
        if (name == null) {
            //System.out.println("Incorrect credentials.");
            return Optional.empty();
        }

        final var emailAddress = EmailAddress.buildFrom(split.get(split.size() - 1));

        return Optional.ofNullable(new Student.Builder().name(name).emailAddress(emailAddress).build());
    }

    public Optional<PointsInput> parseScore(String input) {
        if (!validPointsPattern.matcher(input).matches()) {
            return Optional.empty();
        }
        final var split = Arrays.stream(input.split(INPUT_SPLIT_PATTERN_REGEX)).mapToInt(Integer::parseInt).toArray();
        if (split.length - 1 != platform.getCoursesIds().size()) {
            return Optional.empty();
        }
        return Optional.of(new PointsInput(split[0], Arrays.copyOfRange(split, 1, split.length)));
    }
}
