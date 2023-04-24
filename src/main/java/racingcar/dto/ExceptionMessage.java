package racingcar.dto;

public class ExceptionMessage {

    private final String errorMessage;

    public ExceptionMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
