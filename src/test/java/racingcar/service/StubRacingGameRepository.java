package racingcar.service;

import racingcar.domain.RacingGame;
import racingcar.repository.RacingGameRepository;
import racingcar.dao.entity.InsertGameEntity;

public class StubRacingGameRepository implements RacingGameRepository {

    @Override
    public InsertGameEntity save(final RacingGame racingGame) {
        return new InsertGameEntity(1, racingGame);
    }
}
