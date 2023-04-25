package racingcar.view;

import java.util.Scanner;
import racingcar.utils.IntegerParser;

public class InputView {

    private static final String READ_CAR_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String READ_ATTEMPT_NUMBER_MESSAGE = "시도할 회수는 몇회인가요?";

    private static final Scanner scanner = new Scanner(System.in);

    public static String readCarNames() {
        System.out.println(READ_CAR_NAME_MESSAGE);
        return scanner.nextLine();
    }

    public static int readAttemptNumber() {
        try {
            System.out.println(READ_ATTEMPT_NUMBER_MESSAGE);
            String input = scanner.nextLine();
            return IntegerParser.parse(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readAttemptNumber();
        }
    }
}
