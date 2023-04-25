package racingcar.view.input;

import java.util.Scanner;
import racingcar.controller.ConsoleCommand;

public class InputView {

    private static final String CAR_NAMES_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String TRY_COUNT_INPUT_MESSAGE = "시도할 회수는 몇회인가요?";

    private final Scanner scanner;
    private final InputValidator inputValidator = new InputValidator();

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readCarNames() {
        System.out.println();
        System.out.println(CAR_NAMES_INPUT_MESSAGE);
        return scanner.nextLine();
    }

    public int readTryCount() {
        System.out.println();
        System.out.println(TRY_COUNT_INPUT_MESSAGE);
        String gameTry = scanner.nextLine();
        inputValidator.validateGameTryRange(gameTry);
        return Integer.parseInt(gameTry);
    }

    public ConsoleCommand readCommand() {
        System.out.println("원하시는 메뉴의 번호를 입력하세요.");
        System.out.println("1. 게임 진행");
        System.out.println("2. 모든 게임 결과 보기");
        System.out.println("3. 프로그램 종료");
        return ConsoleCommand.mapConsoleInputToCommand(scanner.nextLine());
    }
}
