package racingcar.view;

import java.util.Scanner;

public class InputView {

    private final InputViewValidator inputViewValidator = new InputViewValidator();

    public String readCarNames() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int readTryNum() {
        Scanner scanner = new Scanner(System.in);
        String tryNum = scanner.nextLine();
        inputViewValidator.validateTryNum(tryNum);
        return Integer.parseInt(tryNum);
    }
}
