package racingcar.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public void showEnterCarNameMessage() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
    }

    public void showEnterCountMessage() {
        System.out.println("시도할 회수는 몇회인가요?");
    }

    public String getInputUntilExist() {
        try {
            return getInput();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getInputUntilExist();
        }
    }

    private String getInput() {
        return readLine();
    }

    private String readLine() {
        return scanner.nextLine().strip();
    }
}
