package racingcar.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public class ExceptionResponseDto {
    private final String message;
    
    public ExceptionResponseDto(final String message) {
        this.message = message;
    }
}
