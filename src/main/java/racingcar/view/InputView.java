package racingcar.view;

import racingcar.constant.ExceptionMessage;

import java.util.Scanner;

public class InputView {
    private static final int NEGATIVE_NUMBER_INDICATOR = 0;
    private final static String PRINT_REQUEST_CAR_NAME
            = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private final static String PRINT_REQUEST_TRY_COUNT = "시도할 회수는 몇회인가요?";

    private Scanner scanner = new Scanner(System.in);

    public String inputCarName() {
        System.out.println(PRINT_REQUEST_CAR_NAME);
        return scanner.nextLine();
    }

    public int inputTryCount() {
        try {
            System.out.println(PRINT_REQUEST_TRY_COUNT);
            int tryCount = Integer.parseInt(scanner.nextLine());
            return validPositiveNumber(tryCount);
        } catch (Exception e) {
            throw new IllegalArgumentException(ExceptionMessage.EXCEPTION_NOT_NUMBER_MESSAGE.getExceptionMessage());
        }
    }

    private int validPositiveNumber(int number) {
        if (number <= NEGATIVE_NUMBER_INDICATOR) {
            throw new IllegalArgumentException(ExceptionMessage.EXCEPTION_TRY_COUNT_MESSAGE.getExceptionMessage());
        }
        return number;
    }
}
