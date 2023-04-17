package racingcar.exception;

public class CarNameLengthException extends RuntimeException {

    private static final int MINIMUM_LENGTH = 1;
    private static final int MAXIMUM_LENGTH = 5;

    public CarNameLengthException() {
        super(String.format(
                "[ERROR] 자동차 이름의 길이는 %d자 이상, %d자 이하여야 합니다.",
                MINIMUM_LENGTH, MAXIMUM_LENGTH));
    }
}
