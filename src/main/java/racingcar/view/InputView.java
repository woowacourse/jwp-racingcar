package racingcar.view;

import racingcar.utils.Parser;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String COMMA = ",";
    private static final String requestCarNameMessage = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String requestTryCountMessage = "시도할 회수는 몇회인가요?";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> requestCarName() {
        System.out.println(requestCarNameMessage);

        try {
            return Parser.sliceByComma(input());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestCarName();
        }
    }

    public static int requestTryCount() {
        System.out.println(requestTryCountMessage);

        try {
            return Integer.parseInt(input());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestTryCount();
        }
    }

    private static String input() {
        return scanner.nextLine();
    }
}
