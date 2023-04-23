package racingcar.repositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;
import racingcar.dao.entity.GameId;
import racingcar.dao.entity.WinnerEntity;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.domain.Winners;

public class RacingGameMapper {

    private RacingGameMapper() {
    }

    public static GameEntity toGameEntity(final RacingGame racingGame) {
        return new GameEntity(racingGame.getGameId().getValue(), racingGame.getCount().getTargetCount());
    }

    public static List<CarEntity> toCarEntities(final List<Car> cars, final GameId gameId) {
        return cars.stream()
                .map(car -> CarEntity.of(car, gameId))
                .collect(Collectors.toList());
    }

    public static List<WinnerEntity> toWinnerEntity(final Winners winners) {
        return winners.getCars()
                .stream()
                .map(car -> new WinnerEntity(winners.getGameId().getValue(), car.getCarId().getValue()))
                .collect(Collectors.toList());
    }

    public static List<RacingGame> toDomain(final List<GameEntity> gameEntities, final List<CarEntity> carEntities) {
        final List<RacingGame> result = new ArrayList<>();
        for (final GameEntity gameEntity : gameEntities) {
            final List<CarEntity> carEntitiesByGameId = carEntities.stream()
                    .filter(carEntity -> carEntity.getGameId().equals(gameEntity.getGameId()))
                    .collect(Collectors.toList());
            result.add(toDomain(gameEntity, carEntitiesByGameId));
        }
        return result;
    }

    public static RacingGame toDomain(final GameEntity gameEntity, final List<CarEntity> savedCarEntities) {
        final List<Car> carWithIds = savedCarEntities.stream()
                .map(CarEntity::toDomain)
                .collect(Collectors.toList());
        return new RacingGame(gameEntity.getGameId().getValue(), carWithIds, gameEntity.getTrialCount());
    }

}
