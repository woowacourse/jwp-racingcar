package racingcar.view;

import java.util.Arrays;

public enum ApplicationCommand {
    CONSOLE("C"),
    WEB("W"),
    ;

    private final String command;

    ApplicationCommand(String command) {
        this.command = command;
    }

    public static ApplicationCommand getByCommand(String command) {
        return Arrays.stream(values())
                .filter(applicationCommand -> applicationCommand.command.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커멘드 입력"));
    }
}
