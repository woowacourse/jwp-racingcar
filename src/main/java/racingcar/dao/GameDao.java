package racingcar.dao;

import racingcar.entity.Game;

import java.util.List;

interface GameDao {

    Long save(final Game game);

    List<Game> findAll();
}
