package racingcar.dao;

import racingcar.Entity.Player;

import java.util.List;

public interface PlayerDao {
    void saveAll(final List<Player> players);
}
