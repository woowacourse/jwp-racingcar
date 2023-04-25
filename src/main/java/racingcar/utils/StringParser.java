package racingcar.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringParser {

    public static List<String> splitByComma(final String input) {
        return Arrays.stream(input.split(",", -1))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
