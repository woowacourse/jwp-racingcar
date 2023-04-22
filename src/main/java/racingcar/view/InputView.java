package racingcar.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return Arrays.asList(input().split(","));
    }

    public static int inputTryCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        try {
            return Integer.parseInt(input());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("정수만 입력해 주세요.");
        }
    }

    private static String input() {
        return scanner.nextLine();
    }
}
