package tracker.controller.command.commands;

import tracker.controller.command.Command;
import tracker.data.platform.Platform;

import java.util.Scanner;

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
        do {
            final var inputStr = scanner.nextLine();
            if (inputStr.equals("back")) {
                break;
            }
            //System.out.println(platform.getAccountDetails(inputStr));
        } while (true);

    }
}
