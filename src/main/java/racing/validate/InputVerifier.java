package racing.validate;

public class InputVerifier {

    private static final String CANT_CONTAIN_SPACE = "자동차 이름에 공백이 포함될 수 없습니다.";
    private static final String UNSUITABLE_LENGTH = "자동차 이름은 1자~5자만 입력할 수 있습니다.";
    private static final String INPUT_ONLY_DIGIT = "시도 회수는 숫자만 입력할 수 있습니다.";

    private InputVerifier() {
    }

    private static void checkSpace(String name) {
        if (name.contains(" ")) {
            throw new IllegalArgumentException(CANT_CONTAIN_SPACE);
        }
    }

    private static void checkNameLength(String name) {
        if (isValueLength(name)) {
            throw new IllegalArgumentException(UNSUITABLE_LENGTH);
        }
    }

    private static boolean isValueLength(String name) {
        return name.length() < 1 || 5 < name.length();
    }

    public static void checkInputTypeIsNumber(String input) {
        if (!input.matches("[0-9]+")) {
            throw new IllegalArgumentException(INPUT_ONLY_DIGIT);
        }
    }
}
