package racingcar.repositoryImpl;

import java.util.ArrayList;
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

    //매퍼가 이렇게 많은 역할을 하고 있는게 맞는지 애매해지네요 어디까지 repository 의 책임일까요
    public static List<RacingGame> toDomain(final List<GameEntity> gameEntities, final List<CarEntity> carEntities) {
        final List<RacingGame> result = new ArrayList<>();
        for (final GameEntity gameEntity : gameEntities) {
            final List<CarEntity> carEntitiesByGameId = carEntities.stream()
                    .filter(carEntity -> carEntity.getGameId() == gameEntity.getGameId())
                    .collect(Collectors.toList());
            result.add(toDomain(gameEntity, carEntitiesByGameId));
        }
        return result;
    }

    public static RacingGame toDomain(final GameEntity gameEntity, final List<CarEntity> savedCarEntities) {
        final List<Car> carWithIds = savedCarEntities.stream()
                .map(carEntity -> new Car(carEntity.getName(), carEntity.getPosition(), carEntity.getCarId()))
                .collect(Collectors.toList());
        return new RacingGame(gameEntity.getGameId(), carWithIds, gameEntity.getTrialCount());
    }

}
