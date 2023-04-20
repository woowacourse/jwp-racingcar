package racingcar.database;

import racingcar.domain.RacingGame;
import racingcar.dto.response.RacingGameResponseDto;

import java.util.List;

public class InMemoryDatabase implements Database {

    @Override
    public void save(RacingGame racingGame, int trialCount) {

    }

    @Override
    public List<RacingGameResponseDto> findAllHistories() {
        return null;
    }
}
