package racingcar.view;

import java.util.Scanner;

public class InputView {
    private static final String NUMERIC_FORMAT = "^[0-9]*$";
    private static final Scanner sc = new Scanner(System.in);

    public static String readCarNames() {
        printMessage(Message.ASK_CAR_NAMES);
        return readLine();
    }

    public static int readCount() {
        printMessage(Message.ASK_TRY_COUNT);
        String input = readLine();
        validateCount(input);

        return Integer.parseInt(input);
    }

    private static void validateCount(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
        }
    }

    private static boolean isNumeric(String input) {
        return input.matches(NUMERIC_FORMAT);
    }

    private static String readLine() {
        return sc.nextLine();
    }

    private static void printMessage(Message message) {
        System.out.println(message.value);
    }

    private enum Message {
        ASK_CAR_NAMES("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분)."),
        ASK_TRY_COUNT("시도할 횟수는 몇회인가요?");

        private final String value;

        Message(String value) {
            this.value = value;
        }
    }

}
