package tracker.controller.input;

public enum Action {
    EXIT, UNKNOWN, EMPTY_INPUT ;

    public static Action from(String input) {
        if (input.trim().equals("")) {
            return EMPTY_INPUT;
        }
        try {
            return Action.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return Action.UNKNOWN;
        }
    }
}
