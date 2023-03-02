package racingcar.exception;

public class CarNameLengthException extends RuntimeException {
    private static final String CAR_NAME_LENGTH_EXCEPTION_MESSAGE = "[ERROR] 공백을 제외한 이름의 길이가 1이상 5자 이하이어야 합니다";

    public CarNameLengthException() {
        super(CAR_NAME_LENGTH_EXCEPTION_MESSAGE);
    }
}
