package racingcar.repositoryImpl;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;
import racingcar.dao.entity.WinnerEntity;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;

public class RacingGameMapper {

    private RacingGameMapper() {
    }

    public static GameEntity toGameEntity(final RacingGame racingGame) {
        return new GameEntity(racingGame.getGameId(), racingGame.getCount().getTargetCount());
    }

    public static List<CarEntity> toCarEntities(final List<Car> cars, final int gameId) {
        return cars.stream()
                .map(car -> CarEntity.fromDomain(car, gameId))
                .collect(Collectors.toList());
    }

    public static List<WinnerEntity> toWinnerEntity(final RacingGame racingGame) {
        return racingGame.findWinner()
                .stream()
                .map(car -> WinnerEntity.fromDomain(car, racingGame.getGameId()))
                .collect(Collectors.toList());
    }

    public static RacingGame toDomain(final GameEntity gameEntity, final List<CarEntity> savedCarEntities) {
        final List<Car> carWithIds = savedCarEntities.stream()
                .map(carEntity -> new Car(carEntity.getName(), carEntity.getPosition(), carEntity.getCarId()))
                .collect(Collectors.toList());
        return new RacingGame(gameEntity.getGameId(), carWithIds, gameEntity.getTrialCount());
    }
}
