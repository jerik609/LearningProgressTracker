package tracker;

import tracker.controller.Controller;
import tracker.controller.command.Executor;
import tracker.controller.input.Action;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var executor = new Executor();
        final var controller = new Controller(scanner, executor);
        controller.run();
    }
}
