package racingcar.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String CAR_NAME_DELIMITER = ",";

    static Scanner scanner = new Scanner(System.in);

    public static String inputCarNames() {
        return scanner.nextLine();
    }

    public static int inputTryTimes() {
        return scanner.nextInt();
    }
}
