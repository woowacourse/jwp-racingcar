package racingcar.dto;

public class ExceptionMessageDTO {

    private boolean success = false;
    private String message;

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
