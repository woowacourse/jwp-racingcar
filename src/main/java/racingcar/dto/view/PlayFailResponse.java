package racingcar.dto.view;

public class PlayFailResponse {

    private final String message;

    public PlayFailResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
