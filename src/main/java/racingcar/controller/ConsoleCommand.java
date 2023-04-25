package racingcar.controller;

import java.util.Arrays;

public enum ConsoleCommand {
    SAVE("1"),
    FIND("2"),
    QUIT("3");

    private final String consoleInput;

    ConsoleCommand(final String consoleInput) {
        this.consoleInput = consoleInput;
    }

    public String getConsoleInput() {
        return consoleInput;
    }

    public static ConsoleCommand mapConsoleInputToCommand(String consoleInput) {
        return Arrays.stream(ConsoleCommand.values())
                .filter(consoleCommand -> consoleCommand.getConsoleInput().equals(consoleInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }
}
