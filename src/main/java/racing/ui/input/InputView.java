package racing.ui.input;

import racing.ui.validate.InputVerifier;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_CAR_NAME = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String INPUT_COUNT = "시도할 회수는 몇회인가요?";
    private static final String INPUT_BEFORE_GAME = "이전 게임들의 결과를 보시겠습니까?(예: y, 아니오: n)";
    private static final String INPUT_RE_GAME = "게임을 다시 시작하시겠습니까?(예: y, 아니오: n)";

    static Scanner sc = new Scanner(System.in);

    public static String inputCarsName() {
        System.out.println(INPUT_CAR_NAME);
        return sc.next();
    }

    public static String inputCount() {
        System.out.println(INPUT_COUNT);
        return InputVerifier.checkInputTypeIsNumber(sc.next());
    }

    public static String getShowBeforeGame() {
        System.out.println(INPUT_BEFORE_GAME);
        return sc.next();
    }

    public static boolean getReGame() {
        System.out.println(INPUT_RE_GAME);
        String answer = sc.next();
        return answer.equals("y");
    }
}
