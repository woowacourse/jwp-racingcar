package racingcar.view;

import java.util.Arrays;

public enum Command {

    NEW("n"),
    EXIT("x");

    private final String input;

    Command(String input) {
        this.input = input;
    }

    public static Command of(String input) {
        return Arrays.stream(values())
                .filter(it -> it.inputMatches(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다"));
    }

    private boolean inputMatches(String input) {
        return this.input.equals(input);
    }
}
