package racingcar.dao;

import java.util.Collections;
import java.util.List;

public class PlayerConsoleDao implements PlayerDao {

    @Override
    public void saveAllPlayers(Long id, List<Player> players) {
        return;
    }

    @Override
    public List<Player> findAll() {
        return Collections.emptyList();
    }
}
