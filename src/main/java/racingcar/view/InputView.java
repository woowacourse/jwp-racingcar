package racingcar.view;

import racingcar.utils.Parser;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String requestCarNameMessage = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String requestTryCountMessage = "시도할 회수는 몇회인가요?";

    private final Parser parser;
    private final Scanner scanner;

    public InputView(Parser parser, Scanner scanner) {
        this.parser = parser;
        this.scanner = scanner;
    }

    public List<String> requestCarName() {
        System.out.println(requestCarNameMessage);
        return parser.sliceByComma(input());
    }

    public int requestTryCount() {
        System.out.println(requestTryCountMessage);
        return parser.parseIntFrom(input());
    }

    private String input() {
        return scanner.nextLine();
    }
}
