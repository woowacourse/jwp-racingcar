package racing.ui.input;

import java.util.Scanner;
import racing.validate.InputVerifier;

public class InputView {

    private InputView() {
    }

    private static final String INPUT_CAR_NAME = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String INPUT_COUNT = "시도할 회수는 몇회인가요?";

    static Scanner sc = new Scanner(System.in);

    public static String inputCarsName() {
        System.out.println(INPUT_CAR_NAME);
        return sc.next();
    }

    public static int inputCount() {
        System.out.println(INPUT_COUNT);
        String inputCount = sc.next();
        InputVerifier.checkInputTypeIsNumber(inputCount);
        return Integer.parseInt(inputCount);
    }
}
