package racingcar.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GameInputDto {
    private final String carNames;
    private final int tryNumber;
    
    @Builder
    public GameInputDto(final String carNames, final int tryNumber) {
        this.carNames = carNames;
        this.tryNumber = tryNumber;
    }
}
