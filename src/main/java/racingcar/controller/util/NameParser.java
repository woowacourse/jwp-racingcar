package racingcar.controller.util;

import static java.util.Collections.addAll;

import java.util.ArrayList;
import java.util.List;

public class NameParser {

    private static final String delimiter = ",";

    public static List<String> getSlicedName(final String input) {
        validateDelimiter(input);

        List<String> splitNames = new ArrayList<>();
        addAll(splitNames, input.replace(" ", "").split(delimiter));

        return splitNames;
    }

    private static void validateDelimiter(final String input) {
        if (!input.contains(delimiter)){
            throw new IllegalArgumentException("쉼표로 이름을 구분해주세요.");
        }
    }
}
