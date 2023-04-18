package racing.console.ui.input;

public class InputVerifier {

    private static final String INPUT_ONLY_DIGIT = "시도 회수는 숫자만 입력할 수 있습니다.";

    private InputVerifier() {
    }

    public static void checkInputTypeIsNumber(String input) {
        if (!input.matches("[0-9]+")) {
            throw new IllegalArgumentException(INPUT_ONLY_DIGIT);
        }
    }
}
