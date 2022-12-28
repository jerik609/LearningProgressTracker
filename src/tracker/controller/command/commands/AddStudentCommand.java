package tracker.controller.command.commands;

import tracker.controller.command.Command;
import tracker.input.Input;

import java.util.Scanner;

public class AddStudentCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Enter student credentials or 'back' to return:");
        try (final var scanner = new Scanner(System.in)) {
            var addedCount = 0;
            do {
                final var input = scanner.nextLine();
                if (input.equals("back")) {
                    System.out.println("Total " + addedCount + " students have been added.");
                    return;
                }
                if (Input.parseStudentStr(input) != null) {
                    System.out.println("The student has been added.");
                    ++addedCount;
                }
            } while (true);
        }
    }
}
