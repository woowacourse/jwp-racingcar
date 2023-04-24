package racingcar.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String CAR_NAME_DELIMITER = ",";

    static Scanner scanner = new Scanner(System.in);

    public static List<String> inputCarNames() {
        List<String> names = List.of(scanner.nextLine().split(CAR_NAME_DELIMITER));
        validateName(names);
        return names;
    }

    private static void validateName(List<String> names) {
        if (names.size() <= 0) {
            throw new IllegalArgumentException("사용자를 올바르게 입력하지 않았습니다");
        }
    }

    public static int inputTryTimes() {
        int count = scanner.nextInt();
        validateCountSize(count);
        return count;
    }

    private static void validateCountSize(int count) {
        if (count < 1 || count > 20) {
            throw new IllegalArgumentException("최소 1회 이상 최대 20회 이하로 실행해야 합니다");
        }
    }
}
