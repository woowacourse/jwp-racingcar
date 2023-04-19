package racingcar.view;

import java.util.Arrays;

public enum ConsoleCommand {

    PLAY("play"),
    RECORDS("records");

    private final String command;

    ConsoleCommand(final String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static ConsoleCommand match(String command) {
        return Arrays.stream(values())
                .filter(value -> value.command.equalsIgnoreCase(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어를 입력했습니다."));
    }
}
