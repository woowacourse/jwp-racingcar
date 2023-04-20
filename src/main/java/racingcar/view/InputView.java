package racingcar.view;

import java.util.Scanner;

public class InputView {

    public static final String FORMAT_OF_NUMBER = "\\d+";
    private final Scanner scanner;
    private final OutputView outputView;

    public InputView(final Scanner scanner, final OutputView outputView) {
        this.scanner = scanner;
        this.outputView = outputView;
    }

    public String readCarNames() {
        outputView.printCarNameInputGuide();
        return scanner.nextLine();
    }

    public int readGameRound() {
        outputView.printGameRoundGuide();

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
