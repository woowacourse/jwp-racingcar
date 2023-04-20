package racingcar.dao;

import java.util.List;
import racingcar.entity.Player;

public interface PlayerDao {

    void insert(List<Player> players);

    List<Player> findAll();
}
