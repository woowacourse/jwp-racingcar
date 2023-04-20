package racingcar.dao;

import racingcar.entity.Player;

import java.util.List;

public interface PlayerDao {

    void insert(final long id, final String name, final int position, boolean winner);
    List<Player> findAllPlayer(long playResultId);
}
