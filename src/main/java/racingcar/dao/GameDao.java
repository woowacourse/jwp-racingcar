package racingcar.dao;

import racingcar.entity.Game;

public interface GameDao {
    int saveAndGetId(final Game game);
}
