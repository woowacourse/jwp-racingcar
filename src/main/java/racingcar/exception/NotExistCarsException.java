package racingcar.exception;

public class NotExistCarsException extends CustomException {

    public NotExistCarsException() {
        super("게임에 참여한 자동차가 없습니다.");
    }
}
