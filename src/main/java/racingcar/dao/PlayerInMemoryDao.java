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
    public void insert(final long playResultId, final String name, final int position, boolean winner) {
        memory.put(id, new Player(id++, playResultId, name, position, winner));
    }

    @Override
    public List<Player> findAllPlayer(final long playResultId) {
        return new ArrayList<>(memory.values());
    }
}
