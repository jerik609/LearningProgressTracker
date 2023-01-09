package tracker.controller.command.commands;

import tracker.controller.command.Command;
import tracker.data.platform.Platform;

public class NotifyCommand implements Command {
    private final Platform platform;

    public NotifyCommand(Platform platform) {
        this.platform = platform;
    }

    @Override
    public void execute() {
        platform.notifyCompleted();
    }
}
