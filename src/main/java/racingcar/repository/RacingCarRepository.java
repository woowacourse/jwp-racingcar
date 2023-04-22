package racingcar.repository;

import racingcar.domain.RacingGame;

import java.util.List;

public interface RacingCarRepository {

    void save(final RacingGame racingGame, final int trialCount);

    List<RacingGame> findAll();
}
