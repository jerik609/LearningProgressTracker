package tracker.controller.input;

import tracker.controller.Controller;
import tracker.controller.command.commands.*;
import tracker.controller.command.Command;
import tracker.data.platform.Platform;

import java.util.Scanner;

public class CommandFactory {
    private final Scanner scanner;
    private final Controller controller;
    private final Platform platform;


    public CommandFactory(Scanner scanner, Controller controller, Platform platform) {
        this.scanner = scanner;
        this.controller = controller;
        this.platform = platform;
    }

    public Command getCommandFromAction(Action action) {
        return switch (action) {
            case EXIT -> new StopCommand(controller);
            case UNKNOWN -> new UnknownCommand();
            case EMPTY_INPUT -> new EmptyInputCommand();
            case ADD_STUDENTS -> new AddStudentCommand(scanner, platform);
            case BACK -> new BackCommand();
            case LIST_STUDENTS -> new ListCommand(platform);
            case ADD_POINTS -> new AddPointsCommand(scanner, platform);
            case FIND_STUDENT -> new FindCommand(scanner, platform);
        };
    }
}
