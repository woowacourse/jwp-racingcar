package racingcar.dao;

import racingcar.entity.RacingGame;

import java.util.List;

public interface RacingGameRepository {

    Long save(final RacingGame racingGame);

    List<RacingGame> findAll();
}
