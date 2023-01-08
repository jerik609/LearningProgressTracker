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

    private <T> void printWhileSame(List<Map.Entry<Integer, T>> list) {
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

    @Override
    public void execute() {
        System.out.println("Type the name of a course to see details or 'back' to quit");

        System.out.print("Most popular:");
        printWhileSame(platform.getSortedTotalEnrolledStudentsPerCourse(Platform.SORT_DESC));
        System.out.print("Least popular:");
        printWhileSame(platform.getSortedTotalEnrolledStudentsPerCourse(Platform.SORT_ASC));
        System.out.print("Highest activity:");
        printWhileSame(platform.getSortedTotalTasksPerCourse(Platform.SORT_DESC));
        System.out.print("Lowest activity:");
        printWhileSame(platform.getSortedTotalTasksPerCourse(Platform.SORT_ASC));
        System.out.print("Easiest course:");
        printWhileSame(platform.getSortedAverageScorePerCourse(Platform.DOUBLE_SORT_DESC));
        System.out.print("Hardest course:");
        printWhileSame(platform.getSortedAverageScorePerCourse(Platform.DOUBLE_SORT_ASC));

        do {
            final var inputStr = scanner.nextLine();
            if (inputStr.equals("back")) {
                break;
            }
        } while (true);

    }
}
