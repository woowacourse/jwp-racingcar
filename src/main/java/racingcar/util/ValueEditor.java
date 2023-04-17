package racingcar.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ValueEditor {

    private static final String COMMA = ",";
    private static final String COMMA_WITH_SPACE = ", ";
    private static final String NUMBER_FORMAT_EXCEPTIONS_MESSAGE = "정수만 입력가능 합니다.";

    private ValueEditor() {
    }

    public static String removeSpace(String input) {
        return input.replaceAll(Regex.SPACE.regex, Regex.NO_SPACE.regex);
    }

    public static List<String> splitByComma(String input) {
        return Arrays.stream(input.split(COMMA))
                .collect(Collectors.toList());
    }

    public static int parseStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch(NumberFormatException ex) {
            throw new IllegalArgumentException(NUMBER_FORMAT_EXCEPTIONS_MESSAGE);
        }
    }

    public static String joinWithComma(List<String> inputs) {
        return String.join(COMMA_WITH_SPACE, inputs);
    }

    private enum Regex {
        SPACE(" "), NO_SPACE("");
        private final String regex;

        Regex(String regex) {
            this.regex = regex;
        }
    }
}
