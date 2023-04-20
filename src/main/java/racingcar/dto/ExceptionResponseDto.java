package racingcar.dto;

import lombok.Getter;

@Getter
public class ExceptionResponseDto {
    private final String message;
    
    public ExceptionResponseDto(final String message) {
        this.message = message;
    }
}
