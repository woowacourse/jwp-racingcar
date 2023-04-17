package racingcar.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputConvertor {

    private static final int MINIMUM_TRY_COUNT = 1;
    private static final String DELIMITER = ",";

    private InputConvertor() {
    }

    public static List<String> carNames(final String names) {
        if (!names.contains(DELIMITER)) {
            throw new IllegalArgumentException("[ERROR] 구분자(" + DELIMITER + ")가 필요해요.");
        }
        return Arrays.stream(names.split(DELIMITER))
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public static int tryCount(final String inputCount) {
        int tryCount;
        try {
            tryCount = Integer.parseInt(inputCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력가능해요");
        }
        if (tryCount < MINIMUM_TRY_COUNT) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 " + MINIMUM_TRY_COUNT + "미만일 수 없어요.");
        }

        return tryCount;
    }

}
