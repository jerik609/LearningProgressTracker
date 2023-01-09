package tracker.controller.command.commands;

import tracker.controller.command.Command;
import tracker.data.platform.Platform;

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
            platform.getCourseIdsForCourseName(inputStr).forEach(id -> {
                printCourseDetails(platform, id);
            });
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
        System.out.print("Most popular:");
        printWhileSame(platform, platform.getSortedTotalEnrolledStudentsPerCourse(Platform.sortLongDesc()));
        System.out.print("Least popular:");
        printWhileSame(platform, platform.getSortedTotalEnrolledStudentsPerCourse(Platform.sortLongAsc()));
        System.out.print("Highest activity:");
        printWhileSame(platform, platform.getSortedTotalTasksPerCourse(Platform.sortLongDesc()));
        System.out.print("Lowest activity:");
        printWhileSame(platform, platform.getSortedTotalTasksPerCourse(Platform.sortLongAsc()));
        System.out.print("Easiest course:");
        printWhileSame(platform, platform.getSortedAverageScorePerCourse(Platform.sortDoubleDesc()));
        System.out.print("Hardest course:");
        printWhileSame(platform, platform.getSortedAverageScorePerCourse(Platform.sortDoubleAsc()));
    }

    public static void printCourseDetails(Platform platform, int courseId) {
        System.out.println(platform.getCourseName(courseId));
        System.out.println("id points completed");
        platform.topLearners(courseId)
                .entrySet()
                .stream()
                .sorted(Platform.<String>sortLongDesc().thenComparing(Map.Entry::getKey))
                .toList()
                .forEach(entry -> {
                    final var totalPoints = platform.getAccount(entry.getKey()).get().getCourses().get(courseId).getTotalPoints();
                    final var requiredPoints = platform.getCourse(courseId).requiredPoints();
                    System.out.printf("%s %d %.1f\n",
                            entry.getKey(),
                            totalPoints,
                            (double)requiredPoints / totalPoints);
                    });
    }

}
