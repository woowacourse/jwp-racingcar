package racingcar.ui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WebInputConvertor {

    private static final String DELIMITER = ",";

    private WebInputConvertor() {
    }

    public static List<String> carNames(final String names) {
        if (!names.contains(DELIMITER)) {
            throw new IllegalArgumentException(" 구분자(" + DELIMITER + ")가 필요해요.");
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
            throw new IllegalArgumentException("숫자만 입력가능해요");
        }

        return tryCount;
    }

}
