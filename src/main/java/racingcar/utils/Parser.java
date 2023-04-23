package racingcar.utils;

import org.springframework.stereotype.Component;
import racingcar.exception.ExceptionInformation;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class Parser {

    private static final String COMMA_DELIMITER = ",";

    public List<String> sliceByComma(final String target) {
        validateComma(target);

        return Arrays.stream(target.split(COMMA_DELIMITER))
                .collect(toList());
    }

    private void validateComma(final String names) {
        if (!names.contains(COMMA_DELIMITER)) {
            throw new IllegalArgumentException(ExceptionInformation.NOT_FOUND_COMMA.getExceptionMessage());
        }
    }

    public int parseIntFrom(String target) {
        try {
            return Integer.parseInt(target);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ExceptionInformation.NOT_POSITIVE_INTEGER.getExceptionMessage());
        }
    }
}
