package racingcar.util;

import java.util.List;

public class ListJoiner {

    private static final String DELIMITER = ",";

    public static String join(final List<String> input) {
        return String.join(DELIMITER, input);
    }
}
