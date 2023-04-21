package racingcar.utils;

import racingcar.exception.ExceptionInformation;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Parser {

    private static final String COMMA_DELIMITER = ",";

    public static List<String> sliceByComma(final String target) {
        validateComma(target);

        return Arrays.stream(target.split(COMMA_DELIMITER))
                .collect(toList());
    }

    private static void validateComma(final String names) {
        if (!names.contains(",")) {
            throw new IllegalArgumentException(ExceptionInformation.NOT_FOUND_COMMA.getExceptionMessage());
        }
    }

    public static int parseIntFrom(String target) {
        try {
            return Integer.parseInt(target);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ExceptionInformation.NOT_POSITIVE_INTEGER.getExceptionMessage());
        }
    }
}
