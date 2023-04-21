package racingcar.view;

import java.util.Scanner;

public class InputView {

    private Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return scanner.nextLine().strip();
    }

    public String inputTrialTimes() {
        System.out.println("시도할 횟수는 몇회인가요?");
        return scanner.nextLine().strip();
    }
}
