package racingcar.dto.response;

public class BadResponseDto {
    private final String message;

    public BadResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
