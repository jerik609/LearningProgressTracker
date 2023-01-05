package tracker.controller.command.commands;

import tracker.controller.command.Command;
import tracker.data.platform.Platform;

import java.util.Scanner;

public class FindCommand implements Command {
    private final Scanner scanner;
    private final Platform platform;

    public FindCommand(Scanner scanner, Platform platform) {
        this.scanner = scanner;
        this.platform = platform;
    }

    @Override
    public void execute() {
        System.out.println("Enter an id or 'back' to return");
        do {
            final var inputStr = scanner.nextLine();
            if (inputStr.equals("back")) {
                break;
            }
            System.out.println(platform.getAccountDetails(inputStr));
        } while (true);
    }
}
