package tracker.controller.command.commands;

import tracker.controller.command.Command;
import tracker.data.platform.Platform;
import tracker.input.Input;

import java.util.Scanner;

public class AddStudentCommand implements Command {
    private final Scanner scanner;
    private final Platform platform;

    public AddStudentCommand(Scanner scanner, Platform platform) {
        this.scanner = scanner;
        this.platform = platform;
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
            var student = Input.parseStudentStr(input).orElseThrow();
            platform.createAccount(student);
            System.out.println("The student has been added.");
            ++addedCount;
        } while (true);
    }
}
