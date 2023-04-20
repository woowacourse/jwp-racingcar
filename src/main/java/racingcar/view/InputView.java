package racingcar.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputCarNames() {
        String input = SCANNER.nextLine();
        validateBlank(input);
        validateEmpty(input);
        return input;
    }

    public static int inputTryCount() {
        try {
            String input = SCANNER.nextLine();
            int count = Integer.parseInt(input);
            validatePositiveNumber(count);
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    ExceptionMessage.TRY_COUNT_NOT_NUMBER_MESSAGE.getExceptionMessage());
        }
    }

    public static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.CAR_NAME_BLANK_MESSAGE.getExceptionMessage());
        }
    }

    public static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.CAR_NAME_NOT_INPUT_MESSAGE.getExceptionMessage());
        }
    }

    private static void validatePositiveNumber(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.TRY_COUNT_NOT_POSITIVE_MESSAGE.getExceptionMessage());
        }
    }
}
