package tracker.controller.input;

public enum Action {
    EXIT("exit"),
    UNKNOWN("unknown"),
    EMPTY_INPUT(""),
    ADD_STUDENTS("add students"),
    BACK("back"),
    LIST_STUDENTS("list"),
    ADD_POINTS("add points"),
    STATISTICS("statistics"),
    FIND_STUDENT("find");

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
            if (cmd.commandStr.equals(sanitizedInput)) {
                return cmd;
            }
        }
        return UNKNOWN;
    }
}
