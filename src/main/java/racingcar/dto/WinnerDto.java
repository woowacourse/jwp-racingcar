package racingcar.dto;

import lombok.Getter;

@Getter
public class WinnerDto {
    private final long gameId;
    private final long carId;
    
    public WinnerDto(final long gameId, final long carId) {
        this.gameId = gameId;
        this.carId = carId;
    }
}
