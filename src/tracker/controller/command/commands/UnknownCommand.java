package tracker.controller.command.commands;

import tracker.controller.command.Command;

public class UnknownCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Error: Unknown command!");
    }
}
