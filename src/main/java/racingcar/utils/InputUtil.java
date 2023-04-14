package racingcar.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputUtil {

    private static final int MIN_INPUT_LENGTH = 1;

    public static List<String> splitNames(final String input) {
        validateEmpty(input);
        final List<String> names = Arrays.asList(input.split(","));
        validateDuplicate(names);
        return names;
    }

    private static void validateDuplicate(final List<String> names) {
        final Set<String> set = new HashSet<>(names);
        if (set.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    private static void validateEmpty(final String input) {
        if (input.length() < MIN_INPUT_LENGTH) {
            throw new IllegalArgumentException("빈 이름은 입력할 수 없습니다.");
        }
    }
}
