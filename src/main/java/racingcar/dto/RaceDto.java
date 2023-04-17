package racingcar.dto;

import java.util.List;

public class RaceDto {

    private final int gameId;
    private final List<CarPositionDto> carPositionDtos;
    private final List<CarPositionDto> winners;

    public RaceDto(final int gameId, final List<CarPositionDto> carPositionDtos,
            final List<CarPositionDto> winners) {
        this.gameId = gameId;
        this.carPositionDtos = carPositionDtos;
        this.winners = winners;
    }

    public List<CarPositionDto> getCarPositionDtos() {
        return carPositionDtos;
    }

    public List<CarPositionDto> getWinners() {
        return winners;
    }
}
