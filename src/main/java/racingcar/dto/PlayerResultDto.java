package racingcar.dto;

import java.util.List;

public class PlayerResultDto {
    private final List<CarDto> carDtos;
    private final int gameId;

    private PlayerResultDto(List<CarDto> carDtos, int gameId) {
        this.carDtos = carDtos;
        this.gameId = gameId;
    }

    public static PlayerResultDto of(List<CarDto> carDtos, int gameId) {
        return new PlayerResultDto(carDtos, gameId);
    }

    public List<CarDto> getCarDtos() {
        return carDtos;
    }

    public int getGameId() {
        return gameId;
    }
}
