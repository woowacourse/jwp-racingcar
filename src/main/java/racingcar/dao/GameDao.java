package racingcar.dao;

import racingcar.entity.Game;

import java.util.List;

public interface GameDao {
    int saveAndGetId(final Game game);

    List<Game> findAll();
}
