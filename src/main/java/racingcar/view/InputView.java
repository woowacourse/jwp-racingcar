package racingcar.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_NAMES = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String INPUT_TRY_COUNT = "시도할 회수는 몇회인가요?";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputNames() {
        System.out.println(INPUT_NAMES);
        return SCANNER.next();
    }

    public static int inputTryCount() {
        System.out.println(INPUT_TRY_COUNT);
        return inputNumber();
    }

    private static int inputNumber() {
        try {
            String input = SCANNER.next();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }
}
