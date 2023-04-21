package racing.view;

import java.util.Scanner;

public class InputView {

    private static final String ENTER_CAR_NAMES = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String ENTER_COUNT = "시도할 회수는 몇회인가요?";
    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String getCarNamesInput() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return getInputUntilExist();
    }

    public String getGameCountInput() {
        System.out.println("시도할 회수는 몇회인가요?");
        return getInputUntilExist();
    }

    private String getInputUntilExist() {
        String input = null;
        while (input == null || input.isBlank()) {
            input = getInput();
        }
        return input;
    }

    private String getInput() throws IllegalArgumentException {
        return scanner.nextLine().strip();
    }
}
