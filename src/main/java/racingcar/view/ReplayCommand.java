package racingcar.view;

import java.util.Arrays;

public enum ReplayCommand {
    YES("Y"),
    NO("N");

    private String command;

    ReplayCommand(String command) {
        this.command = command;
    }

    public static ReplayCommand getByCommand(String command) {
        return Arrays.stream(values())
                .filter(findingCommand -> findingCommand.command.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커멘드 입력"));
    }
}
