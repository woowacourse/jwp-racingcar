package racingcar.exception;

public class RepositoryOutOfSpaceException extends RuntimeException {
    public RepositoryOutOfSpaceException() {
        super("저장소가 가득 차거나 새로 저장할 수 없는 상태입니다.");
    }
}
