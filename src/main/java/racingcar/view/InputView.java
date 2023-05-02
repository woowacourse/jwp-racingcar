package racingcar.view;

import java.util.Scanner;

public class InputView {

    private static final String REQUEST_CAR_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String REQUEST_TRY_COUNT_MESSAGE = "시도할 회수는 몇회인가요?";
    private static final Scanner scanner = new Scanner(System.in);

    public static String requestCarName() {
        System.out.println(REQUEST_CAR_NAME_MESSAGE);

        return input();
    }

    public static int requestTryCount() {
        System.out.println(REQUEST_TRY_COUNT_MESSAGE);

        return Integer.parseInt(input());
    }

    private static String input() {
        return scanner.nextLine();
    }
}
