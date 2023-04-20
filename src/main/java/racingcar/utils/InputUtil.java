package racingcar.utils;

public class InputUtil {
    public static String[] splitNames(final String input) {
        final String[] names = input.split(" *, *");
        Validator.checkDuplication(names);
        Validator.checkEmpty(names);
        return names;
    }
}
