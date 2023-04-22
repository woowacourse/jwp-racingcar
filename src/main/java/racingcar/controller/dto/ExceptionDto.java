package racingcar.controller.dto;

public class ExceptionDto {

    private final String exceptionMessage;

    public ExceptionDto(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

}
