package racingcar.dao;

import racingcar.entity.Player;

import java.util.List;

public interface PlayerDao {
    void saveAll(final List<Player> players);

    List<Player> findAll();
}
