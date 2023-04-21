package racingcar.dao;

import racingcar.entity.Player;

import java.util.List;

interface PlayerDao {

    void save(final long gameId, final List<Player> players);

    List<Player> findById(final long gameId);
}
