package racingcar.view;

import java.util.Scanner;

public class InputView {

    public static final String FORMAT_OF_NUMBER = "\\d+";
    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return scanner.nextLine();
    }

    public int readGameRound() {
        System.out.println("시도할 횟수는 몇 회인가요?");

        String gameRoundContent = scanner.nextLine();
        validateGameRound(gameRoundContent);

        return Integer.parseInt(gameRoundContent);
    }

    private void validateGameRound(final String content) {
        if (!content.matches(FORMAT_OF_NUMBER)) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력이 가능합니다.");
        }
    }
}
