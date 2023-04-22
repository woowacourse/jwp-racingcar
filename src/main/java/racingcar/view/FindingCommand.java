package racingcar.view;

import java.util.Arrays;

public enum FindingCommand {
    YES("Y"),
    NO("N");

    private String command;

    FindingCommand(String command) {
        this.command = command;
    }

    public static FindingCommand getByCommand(String command) {
        return Arrays.stream(values())
                .filter(findingCommand -> findingCommand.command.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커멘드 입력"));
    }
}
