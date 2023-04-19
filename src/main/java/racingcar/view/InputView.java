package racingcar.view;

import java.util.Scanner;

public class InputView {

    private static final String READ_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String READ_COUNT_MESSAGE = "시도할 회수는 몇회인가요?";

    private final Parser parser = new Parser();
    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readCarNames() {
        System.out.println(READ_NAMES_MESSAGE);
        return scanner.nextLine();
    }

    public int readCount() {
        System.out.println(READ_COUNT_MESSAGE);
        final String input = scanner.nextLine();
        return parser.parseCount(input);
    }
}
