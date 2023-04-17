package racingcar.service;

import racingcar.dao.entity.GameEntity;
import racingcar.domain.RacingGame;
import racingcar.repository.RacingGameRepository;

public class StubRacingGameRepository implements RacingGameRepository {

    @Override
    public GameEntity save(final RacingGame racingGame) {
        return new GameEntity(1, racingGame);
    }
}
