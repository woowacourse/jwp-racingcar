package racingcar.consolegame.ui;

import java.util.List;
import java.util.Scanner;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/18
 */
public class InputView {

    private static final int MINIMUM_TRY_COUNT = 1;
    private static final String DELIMITER = ",";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> carNames() {
        try {
            String input = input();

            if (!input.contains(DELIMITER)) {
                throw new IllegalArgumentException("[ERROR] 구분자(" + DELIMITER + ")가 필요해요.");
            }

            return List.of(input.split(DELIMITER));
        } catch (IllegalArgumentException e) {
            OutputView.error(e.getMessage());

            return carNames();
        }
    }

    public int tryCount() {
        try {
            return inputTryCount();
        } catch (IllegalArgumentException e) {
            OutputView.error(e.getMessage());

            return tryCount();
        }
    }

    private int inputTryCount() throws IllegalArgumentException {
        int tryCount;

        try {
            tryCount = Integer.parseInt(input());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력가능해요");
        }
        if (tryCount < MINIMUM_TRY_COUNT) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 " + MINIMUM_TRY_COUNT + "미만일 수 없어요.");
        }

        return tryCount;
    }

    private String input() {
        return scanner.nextLine();
    }

}
