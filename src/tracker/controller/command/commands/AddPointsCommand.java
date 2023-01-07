package tracker.controller.command.commands;

import tracker.controller.command.Command;
import tracker.data.platform.Platform;
import tracker.input.Input;

import java.util.Scanner;

public class AddPointsCommand implements Command {
    private final Scanner scanner;
    private final Platform platform;
    private final Input input;

    public AddPointsCommand(Scanner scanner, Platform platform) {
        this.scanner = scanner;
        this.platform = platform;
        this.input = new Input(platform);
    }

    @Override
    public void execute() {
        System.out.println("Enter an id and points or 'back' to return");
        do {
            final var inputStr = scanner.nextLine();
            if (inputStr.equals("back")) {
                break;
            }
            input.parseScore(inputStr).ifPresentOrElse(
                    points -> {
                        if (platform.addPoints(points)) {
                            System.out.println("Points updated.");
                        } else {
                            System.out.println("No student is found for id=" + points.studentId());
                        }},
                    () -> System.out.println("Incorrect points format."));
        } while (true);
    }
}
