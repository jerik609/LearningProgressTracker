package tracker.controller.input;

public enum Action {
    EXIT("exit"),
    UNKNOWN("unknown"),
    EMPTY_INPUT(""),
    ADD_STUDENTS("add students"),
    BACK("back");

    private final String commandStr;

    Action(String commandStr) {
        this.commandStr = commandStr;
    }

    public static Action from(String input) {
        final var sanitizedInput = input.trim().toLowerCase();
        if (sanitizedInput.equals("unknown")) {
            return UNKNOWN;
        }
        for (var cmd : Action.values()) {
            if (cmd.commandStr.equals(input)) {
                return cmd;
            }
        }
        return UNKNOWN;
    }
}
