package racingcar.view;

import java.util.HashMap;
import java.util.Map;

public enum Command {

    NEW("n"),
    EXIT("x"),
    NOT_FOUND("");

    private final String input;

    Command(String input) {
        this.input = input;
    }

    private static final Map<String, Command> CACHE = new HashMap<>();

    static {
        for (Command command : values()) {
            CACHE.put(command.input, command);
        }
    }

    public static Command of(String input) {
        Command command = CACHE.getOrDefault(input, NOT_FOUND);
        if (command == NOT_FOUND) {
            throw new IllegalArgumentException("잘못된 명령어입니다");
        }
        return command;
    }
}
