package tracker.controller.command.commands;

import tracker.controller.command.Command;
import tracker.data.platform.Platform;

import java.util.Scanner;

public class AddPointsCommand implements Command {
    private final Scanner scanner;
    private final Platform platform;

    public AddPointsCommand(Scanner scanner, Platform platform) {
        this.scanner = scanner;
        this.platform = platform;
    }

    @Override
    public void execute() {
        System.out.println("Enter an id and points or 'back' to return");
    }
}
