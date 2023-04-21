package racingcar.dao;

import java.util.List;

public interface PlayerDao {

    void saveAllPlayers(final Long id, final List<Player> players);

    List<Player> findAll();
}
