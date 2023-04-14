package racingcar.service;

import racingcar.domain.RacingGame;
import racingcar.repository.entity.GameEntity;

public class StubRacingGameRepository implements RacingGameRepository {

    @Override
    public GameEntity save(final RacingGame racingGame) {
        return new GameEntity(1, racingGame);
    }
}
