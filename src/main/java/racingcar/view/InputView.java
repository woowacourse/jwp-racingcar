package racingcar.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String INPUT_TRY_COUNT_MESSAGE = "시도할 회수는 몇회인가요?";
    private static final String TRY_COUNT_ERROR_MESSAGE = "시도 횟수는 숫자여야 합니다.";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readCarNames() {
        System.out.println(INPUT_CAR_NAMES_MESSAGE);
        return scanner.nextLine();
    }

    public int readTryCount() {
        System.out.println(INPUT_TRY_COUNT_MESSAGE);
        String tryCount = scanner.nextLine();
        return toNumberFormat(tryCount);
    }

    private int toNumberFormat(String tryCount) {
        try {
            return Integer.parseInt(tryCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(TRY_COUNT_ERROR_MESSAGE);
        }
    }
}
