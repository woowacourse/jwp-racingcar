package racingcar.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return SCANNER.nextLine();
    }

    public static int inputTryCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        return inputNumber();
    }

    private static int inputNumber() {
        String input = SCANNER.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력 값은 숫자여아합니다.\n" + "INPUT : " + input);
        }
    }
}
