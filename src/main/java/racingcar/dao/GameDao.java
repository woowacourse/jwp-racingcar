package racingcar.dao;

import racingcar.entity.Game;

public interface GameDao {
    Game saveAndGetGame(final Game game);
}
