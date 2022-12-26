package tracker.controller.command;

import java.util.LinkedList;
import java.util.Queue;

public class Executor {
    private final Queue<Command> commandQueue = new LinkedList<>();

    public void acceptCommand(Command command) {
        commandQueue.add(command);
    }

    public void execute() {
        while (!commandQueue.isEmpty()) {
            commandQueue.remove().execute();
        }
    }
}
