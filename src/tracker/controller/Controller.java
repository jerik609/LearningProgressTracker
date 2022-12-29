package tracker.controller;

import tracker.controller.command.Executor;
import tracker.controller.input.Action;
import tracker.controller.input.CommandFactory;

import java.util.Scanner;

public class Controller {
    private final Scanner scanner;
    private final Executor executor;
    private final CommandFactory commandFactory;
    private boolean stop;

    public Controller(Scanner scanner, Executor executor) {
        this.scanner = scanner;
        this.executor = executor;
        stop = false;
        commandFactory = new CommandFactory(scanner, this);
    }

    public void run() {
        System.out.println("Learning Progress Tracker");
        do {
            final var input = scanner.nextLine();
            executor.acceptCommand(commandFactory.getCommandFromAction(Action.from(input)));
            executor.execute();
        } while (!stop);
    }

    public void stop() {
        stop = true;
    }
}
