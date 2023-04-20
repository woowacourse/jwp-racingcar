package racingcar.view;

import java.util.Scanner;
import racingcar.dto.RacingGameRequest;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public RacingGameRequest readGameRequest() {
        return RacingGameRequest.of(readCarNames(), readTrialCount());
    }

    private String readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return scanner.nextLine();
    }

    private int readTrialCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 정수형 범위여야합니다.");
        }
    }
}
