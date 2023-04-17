package racingcar.service;

import racingcar.dao.entity.InsertGameEntity;
import racingcar.domain.RacingGameResult;
import racingcar.repository.RacingGameRepository;

public class StubRacingGameRepository implements RacingGameRepository {

    @Override
    public InsertGameEntity save(final RacingGameResult racingGameResult) {
        return new InsertGameEntity(1, racingGameResult);
    }
}
