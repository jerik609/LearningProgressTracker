package tracker;

import tracker.controller.Controller;
import tracker.controller.command.Executor;
import tracker.data.platform.Platform;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var executor = new Executor();
        final var platform = new Platform();
        platform.addCourse("Java", 600);
        platform.addCourse("DSA", 400);
        platform.addCourse("Databases", 480);
        platform.addCourse("Spring", 550);
        final var controller = new Controller(scanner, executor, platform);
        controller.run();
    }
}
