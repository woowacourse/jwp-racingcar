package racingcar.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
public class GameResponseDto {
    private final String winners;
    private final List<CarResponseDto> racingCars;
    
    public GameResponseDto(final String winners, final List<CarResponseDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }
}
