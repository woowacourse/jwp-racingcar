package racingcar.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);
    private static final InputView INPUT_VIEW = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return INPUT_VIEW;
    }

    public String readCarNames() {
        printMessage(Message.ASK_CAR_NAMES);
        return readLine();
    }

    public int readTryCount() {
        printMessage(Message.ASK_TRY_COUNT);

        int input = validateCount(readLine());

        return input;
    }

    private static void validateDuplicatedCarNames(List<String> input) {
        if (input.size() != input.stream().distinct().count()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
    }

    private static int validateCount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.", exception);
        }
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
