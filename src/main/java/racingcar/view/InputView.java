package racingcar.view;

import java.util.Scanner;

import static racingcar.view.OutputConstant.INPUT_CAR_NAME;
import static racingcar.view.OutputConstant.INPUT_TRY_COUNT;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputCarName() {
        System.out.println(INPUT_CAR_NAME);
        return scanner.nextLine();
    }

    public static int inputGameTime() {
        System.out.println(INPUT_TRY_COUNT);
        final String input = scanner.nextLine();
        validateInt(input);
        return Integer.parseInt(input);
    }

    private static void validateInt(final String input) {
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해주세요");
        }
    }
}
