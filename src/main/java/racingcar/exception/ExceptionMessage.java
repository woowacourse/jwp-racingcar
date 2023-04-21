package racingcar.exception;

public enum ExceptionMessage {
    CANNOT_CREATE_RANDOM("랜덤 객체를 생성할 수 없습니다."),
    CANNOT_FIND_MAX_POSITION("최대 거리를 찾을 수 없습니다."),
    ENTER_NAME_WITH_COMMA("쉼표로 이름을 구분해주세요."),
    CANNOT_ACCESS_DATABASE("데이터베이스에 접근할 수 없습니다."),
    ENTER_NAME_CORRECT_LENGTH("이름은 5글자 이하로 입력해주세요."),
    ENTER_POSITIVE_INTEGER("양의 정수를 입력해주세요."),
    INVALID_APPLICATION_TYPE("잘못된 어플리케이션 타입을 입력했습니다."),
    INVALID_EXCEPTION_MESSAGE("잘못된 예외 메세지를 입력했습니다.");

    private final String exceptionMessage;

    ExceptionMessage(final String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
