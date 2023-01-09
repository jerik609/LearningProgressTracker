package tracker.controller.command.commands;

import com.sun.security.jgss.GSSUtil;
import tracker.controller.command.Command;
import tracker.data.platform.Platform;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticsCommand implements Command {
    private final Scanner scanner;
    private final Platform platform;

    public StatisticsCommand(Scanner scanner, Platform platform) {
        this.scanner = scanner;
        this.platform = platform;
    }

    @Override
    public void execute() {
        System.out.println("Type the name of a course to see details or 'back' to quit");
        printPlatformStatistics(platform);
        do {
            final var inputStr = scanner.nextLine();
            if (inputStr.equals("back")) {
                break;
            }
            var courses = platform.getCourseIdsForCourseName(inputStr);
            if (courses.isEmpty()) {
                System.out.println("Unknown course.");
            } else {
                courses.forEach(id -> printCourseDetails(platform, id));
            }
        } while (true);
    }

    private static <T> void printWhileSame(Platform platform, List<Map.Entry<Integer, T>> list) {
        if (!list.isEmpty()) {
            var value = list.get(0).getValue();
            System.out.println(
                    list.stream()
                            .filter(entry -> entry.getValue().equals(value))
                            .map(entry -> platform.getCourseName(entry.getKey()))
                            .collect(Collectors.joining(", ")));
        } else {
            System.out.println("n/a");
        }
    }

    public static void printPlatformStatistics(Platform platform) {
        var mostPopular = platform.getSortedTotalEnrolledStudentsPerCourse(Platform.sortLongDesc());
        var leastPopular = platform.getSortedTotalEnrolledStudentsPerCourse(Platform.sortLongAsc()).stream().filter(
                entry -> !entry.getValue().equals(mostPopular.get(0).getValue())).toList();

        var highestActivity = platform.getSortedTotalTasksPerCourse(Platform.sortLongDesc());
        var lowestActivity = platform.getSortedTotalTasksPerCourse(Platform.sortLongAsc()).stream().filter(
                entry -> !entry.getValue().equals(highestActivity.get(0).getValue())).toList();

        var easiestCourse = platform.getSortedAverageScorePerCourse(Platform.sortDoubleDesc());
        var hardestCourse = platform.getSortedAverageScorePerCourse(Platform.sortDoubleAsc()).stream().filter(
                entry -> !entry.getValue().equals(easiestCourse.get(0).getValue())).toList();

        System.out.print("Most popular: ");
        printWhileSame(platform, mostPopular);
        System.out.print("Least popular: ");
        printWhileSame(platform, leastPopular);

        System.out.print("Highest activity: ");
        printWhileSame(platform, highestActivity);
        System.out.print("Lowest activity: ");
        printWhileSame(platform, lowestActivity);

        System.out.print("Easiest course: ");
        printWhileSame(platform, easiestCourse);
        System.out.print("Hardest course: ");
        printWhileSame(platform, hardestCourse);
    }

    public static void printCourseDetails(Platform platform, int courseId) {
        System.out.println(platform.getCourseName(courseId));
        System.out.println("id\t\tpoints\tcompleted");
        platform.topLearners(courseId)
                .entrySet()
                .stream()
                .sorted(Platform.<String>sortLongDesc().thenComparing(Map.Entry::getKey))
                .filter(entry -> platform.getAccount(entry.getKey()).get().getCourses().get(courseId).getTotalPoints() > 0)
                .forEach(entry -> {
                    final var totalPoints = platform.getAccount(entry.getKey()).get().getCourses().get(courseId).getTotalPoints();
                    final var requiredPoints = platform.getCourse(courseId).requiredPoints();
                    System.out.printf("%s\t\t%d\t",
                            entry.getKey(),
                            totalPoints);
                    System.out.printf(
                            Locale.US, "%,.1f%%\n",
                            requiredPoints == 0 ? BigDecimal.valueOf(0) :
                                    BigDecimal.valueOf(totalPoints * 100)
                                    .divide(BigDecimal.valueOf(requiredPoints), 1, RoundingMode.HALF_UP));
                });
    }
}
