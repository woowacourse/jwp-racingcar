package racingcar.utils;

import java.util.List;

public class SplitCarNames {

    private static final String SPLIT_DELIMITER = ",";

    public static List<String> splitCarNames(String carNames) {
        return List.of(carNames.split(SPLIT_DELIMITER));
    }
}
