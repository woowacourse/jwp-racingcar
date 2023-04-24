package racingcar.view;

import racingcar.utils.Validator;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String CAR_NAME_DELIMITER = ",";

    static Scanner scanner = new Scanner(System.in);

    public static List<String> inputCarNames() {
        String namesInput = scanner.nextLine();
        List<String> names = List.of(namesInput.split(CAR_NAME_DELIMITER));
        Validator.validateProperNamePattern(names);
        Validator.validateNameSize(names);
        Validator.validatePlayerSize(names);
        return names;
    }

    public static int inputTryTimes() {
        Integer count = scanner.nextInt();
        Validator.validateNullcount(count);
        Validator.validateCountSize(count);
        return count;
    }
}
