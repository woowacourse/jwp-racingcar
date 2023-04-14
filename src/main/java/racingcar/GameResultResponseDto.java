package racingcar;

import racingcar.entity.CarEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class GameResultResponseDto {

    private final List<String> winners;
    private final List<CarDto> racingCars;

    public GameResultResponseDto(final List<CarEntity> carEntities) {
        this.winners = carEntities.stream()
                .filter(CarEntity::isWinner)
                .map(CarEntity::getPlayerName)
                .collect(Collectors.toList());
        this.racingCars = carEntities.stream()
                .map(carEntity -> new CarDto(carEntity.getPlayerName(), carEntity.getFinalPosition()))
                .collect(Collectors.toList());
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
