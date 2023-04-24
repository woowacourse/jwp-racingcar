package racingcar.dao;

import racingcar.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerInMemoryDao implements PlayerDao {

    private static long id = 0L;
    private final Map<Long, Player> memory = new HashMap<>();

    @Override
    public void insertAll(final List<Player> players) {
        players.forEach(player -> memory.put(id, player));
    }

    @Override
    public List<Player> findAllPlayer(final long playResultId) {
        return new ArrayList<>(memory.values());
    }
}
