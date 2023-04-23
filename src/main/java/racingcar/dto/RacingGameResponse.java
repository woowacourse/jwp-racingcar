package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dao.entity.CarEntity;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;

public class RacingGameResponse {
    private final Long gameId;
    private final List<String> winners;
    private final List<CarDto> racingCars;

    public RacingGameResponse(Long gameId, List<String> winners, List<CarDto> racingCars) {
        this.gameId = gameId;
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RacingGameResponse of(List<CarEntity> carEntities) {
        List<String> winners = carEntities.stream()
                .filter(CarEntity::isWinner)
                .map(CarEntity::getName)
                .collect(Collectors.toList());

        List<CarDto> carDtos = carEntities.stream()
                .map(carEntity -> new CarDto(carEntity.getName(), carEntity.getPosition()))
                .collect(Collectors.toList());
        return new RacingGameResponse(null, winners, carDtos);
    }

    public static RacingGameResponse of(RacingGame racingGame, Long racingGameId) {
        List<String> winners = racingGame.findWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toList());

        List<CarDto> carDtos = racingGame.getCars().stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
        return new RacingGameResponse(racingGameId, winners, carDtos);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

    public Long getGameId() {
        return gameId;
    }
}
