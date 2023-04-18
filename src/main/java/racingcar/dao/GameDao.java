package racingcar.dao;

import racingcar.entity.Game;

import java.util.Optional;

public interface GameDao {
    Optional<Integer> saveAndGetId(final Game game);
}
