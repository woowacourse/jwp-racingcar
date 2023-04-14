package racingcar.dto;

public class ExceptionMessageDTO {
    private final boolean success = false;
    private final String message;

    public ExceptionMessageDTO(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
