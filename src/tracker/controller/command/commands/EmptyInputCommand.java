package tracker.controller.command.commands;

import tracker.controller.command.Command;

public class EmptyInputCommand implements Command {
    @Override
    public void execute() {
        System.out.println("No input.");
    }
}
