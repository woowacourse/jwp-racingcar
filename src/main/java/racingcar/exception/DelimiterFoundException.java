package racingcar.exception;

public class DelimiterFoundException extends RuntimeExceptionImpl {

    private static final String ENTER_NAME_WITH_DELIMITER = "%s로 이름을 구분해주세요.";

    public DelimiterFoundException(final String delimiter) {
        super(String.format(ENTER_NAME_WITH_DELIMITER, delimiter));
    }
}
