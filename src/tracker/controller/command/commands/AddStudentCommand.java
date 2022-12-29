package tracker.controller.command.commands;

import tracker.controller.command.Command;
import tracker.input.Input;

import java.util.Scanner;

public class AddStudentCommand implements Command {
    private final Scanner scanner;

    public AddStudentCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter student credentials or 'back' to return:");
        var addedCount = 0;
        do {
            final var input = scanner.nextLine();
            if (input.equals("back")) {
                System.out.println("Total " + addedCount + " students have been added.");
                break;
            }
            if (Input.parseStudentStr(input) != null) {
                System.out.println("The student has been added.");
                ++addedCount;
            }
        } while (true);
    }
}
