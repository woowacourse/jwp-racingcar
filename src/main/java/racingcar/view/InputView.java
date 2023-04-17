package racingcar.view;

import java.util.List;
import java.util.Scanner;
import racingcar.dto.GamePlayRequestDto;

public class InputView {
    private final InputParser inputParser;
    private final Scanner scanner;

    public InputView(final InputParser inputParser, final Scanner scanner) {
        this.inputParser = inputParser;
        this.scanner = scanner;
    }

    public GamePlayRequestDto readGamePlayRequest() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        List<String> names = inputParser.splitAndParseNames(scanner.nextLine());

        System.out.println("시도할 회수는 몇회인가요?");
        int count = inputParser.parseInt(scanner.nextLine());

        return new GamePlayRequestDto(names, count);
    }
}
