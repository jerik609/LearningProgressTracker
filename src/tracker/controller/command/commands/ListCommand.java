package tracker.controller.command.commands;

import tracker.controller.command.Command;
import tracker.data.platform.Platform;

public class ListCommand implements Command {
    private final Platform platform;

    public ListCommand(Platform platform) {
        this.platform = platform;
    }

    @Override
    public void execute() {
        var accountIds = platform.getAccountIds();
        if (accountIds.isEmpty()) {
            System.out.println("No students found");
        } else  {
            System.out.println("Students:");
            accountIds.forEach(System.out::println);
        }
    }
}
