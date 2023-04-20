package racingcar.utils;

import racingcar.exception.ExceptionInformation;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Parser {

    public static final String COMMA_DELIMITER = ",";

    public static List<String> sliceByComma(final String target) {
        validateComma(target);

        return Arrays.stream(target.split(COMMA_DELIMITER))
                .collect(toList());
    }

    private static void validateComma(final String names) {
        if (!names.contains(",")) {
            throw new IllegalArgumentException(ExceptionInformation.COMMA_NOT_FOUND_EXCEPTION.getExceptionMessage());
        }
    }
}
