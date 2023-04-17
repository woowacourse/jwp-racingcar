package racingcar.dao;

import java.util.List;
import racingcar.entity.Player;

public interface PlayerDao {
    void saveAll(final List<Player> players);
}
