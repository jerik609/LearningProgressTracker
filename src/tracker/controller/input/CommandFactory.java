package tracker.controller.input;

import tracker.controller.Controller;
import tracker.controller.command.commands.BackCommand;
import tracker.controller.command.Command;
import tracker.controller.command.commands.AddStudentCommand;
import tracker.controller.command.commands.EmptyInputCommand;
import tracker.controller.command.commands.StopCommand;
import tracker.controller.command.commands.UnknownCommand;

import java.util.Scanner;

public class CommandFactory {
    private final Scanner scanner;
    private final Controller controller;


    public CommandFactory(Scanner scanner, Controller controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public Command getCommandFromAction(Action action) {
        return switch (action) {
            case EXIT -> new StopCommand(controller);
            case UNKNOWN -> new UnknownCommand();
            case EMPTY_INPUT -> new EmptyInputCommand();
            case ADD_STUDENTS -> new AddStudentCommand(scanner);
            case BACK -> new BackCommand();
        };
    }
}
