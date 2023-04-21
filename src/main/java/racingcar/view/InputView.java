package racingcar.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public String readCarNames() {
        System.out.println(Message.INPUT_CARS.message);
        return scanner.nextLine();
    }

    public String readMoveCount() {
        System.out.println(Message.INPUT_MOVE_COUNT.message);
        return scanner.nextLine();
    }

    private enum Message {
        INPUT_CARS("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분)."),
        INPUT_MOVE_COUNT("시도할 회수는 몇회인가요?");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
