package racingcar.database;

import racingcar.domain.RacingGame;
import racingcar.dto.response.RacingGameResponseDto;

import java.util.List;

public interface Database {
    void save(RacingGame racingGame, int trialCount);

    List<RacingGameResponseDto> findAllHistories();
}
