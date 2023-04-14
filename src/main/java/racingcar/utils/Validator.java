package racingcar.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Validator {

    public static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 5;

    public static void checkBlank(final String carName) {
        if (carName.isBlank()) {
            throw new IllegalArgumentException("[ERROR] : 빈 이름은 들어올 수 없습니다");
        }
    }

    public static void checkLength(final String carName) {
        if (carName.length() < MIN_NAME_LENGTH || carName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] : 이름 길이는 1~5자 이하여야 합니다");
        }
    }

    public static void checkDuplication(final String[] carNameArr) {
        final Set<String> set = new HashSet<>(Arrays.asList(carNameArr));
        if (set.size() != carNameArr.length) {
            throw new IllegalArgumentException("[ERROR] : 자동차 이름이 중복되었습니다");
        }
    }

    public static void checkEmpty(final String[] carNameArr) {
        if (carNameArr.length < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 빈 이름들은 입력할 수 없습니다.");
        }
    }
}
