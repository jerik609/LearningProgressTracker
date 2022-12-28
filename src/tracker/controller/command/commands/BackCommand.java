package tracker.controller.command.commands;

import tracker.controller.command.Command;

public class BackCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Enter 'exit' to exit the program.");
    }
}
