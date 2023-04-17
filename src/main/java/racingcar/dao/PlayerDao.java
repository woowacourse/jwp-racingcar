package racingcar.dao;

import racingcar.entity.Player;

import java.util.List;

public interface PlayerDao {

    void insert(final String name, final int position, final long playResultId);
    List<Player> findAllPlayer(long playResultId);
}
