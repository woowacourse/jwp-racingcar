package racingcar.controller.console;

import java.util.Arrays;

public enum ConsoleCommand {
    RACE("race"),
    HISTORY("history");

    private final String commandValue;

    ConsoleCommand(final String commandValue) {
        this.commandValue = commandValue;
    }

    public static ConsoleCommand findBy(final String commandValue) {
        return Arrays.stream(ConsoleCommand.values())
                .filter(command -> command.commandValue.equals(commandValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 콘솔 명령어 입니다."));
    }
}
