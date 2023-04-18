package racingcar.view.inputview;

import racingcar.exception.CustomException;
import racingcar.exception.ExceptionStatus;

import java.util.Scanner;

public abstract class InputView {
    private Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    protected String nextLine() {
        final String input = scanner.nextLine().strip();
        validate(input);
        return input;
    }

    private void validate(final String input) {
        if (input.isEmpty() || input.isBlank()) {
            throw new CustomException(ExceptionStatus.EMPTY_INPUT_FORMAT);
        }
    }

    public abstract String inputCarNames();

    public abstract String inputTrialTimes();
}
