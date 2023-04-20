package racingcar.repository;

import racingcar.domain.RacingGame;

import java.util.List;

public interface RacingCarRepository {
    void saveRacingGame(RacingGame racingGame);

    List<RacingGame> findAllEndedRacingGame();
}
