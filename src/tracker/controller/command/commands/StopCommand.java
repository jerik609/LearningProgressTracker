package tracker.controller.command.commands;

import tracker.controller.Controller;
import tracker.controller.command.Command;

public class StopCommand implements Command {
    private final Controller controller;

    public StopCommand(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.stop();
        System.out.println("Bye!");
    }
}
