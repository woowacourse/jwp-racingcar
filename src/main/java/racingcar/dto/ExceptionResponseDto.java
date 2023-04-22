package racingcar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponseDto {

    private final String field;
    private final String message;

    public ExceptionResponseDto(final String message) {
        this.field = null;
        this.message = message;
    }
}
