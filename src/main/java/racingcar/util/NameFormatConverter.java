package racingcar.util;

import java.util.Arrays;
import java.util.List;

public class NameFormatConverter {

    private static final String NAME_DELIMITER = ",";

    public static List<String> splitNameByDelimiter(String names) {
        return Arrays.asList(names.split(NAME_DELIMITER));
    }

    public static String joinNameWithDelimiter(List<String> names) {
        return String.join(NAME_DELIMITER, names);
    }
}
