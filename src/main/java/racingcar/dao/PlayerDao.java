package racingcar.dao;

import racingcar.entity.Player;

import java.util.List;

public interface PlayerDao {

    void insertAll(List<Player> players);
    List<Player> findAllPlayer(long playResultId);
}
