package racingcar.service;

import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import racingcar.domain.RacingGame;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

@Component
public class RacingGameMapper {

    public GameEntity toGameEntity(final int trial) {
        return new GameEntity(trial);
    }

    public List<CarEntity> toCarEntities(final RacingGame racingGame, final int gameId) {
        final Set<String> winners = new HashSet<>(racingGame.findWinners());
        return racingGame.getCars().stream()
                .map(car -> CarEntity.of(car, winners.contains(car.getName()), gameId))
                .collect(toList());
    }
}
