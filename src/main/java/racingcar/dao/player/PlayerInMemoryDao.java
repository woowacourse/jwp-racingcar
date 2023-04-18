package racingcar.dao.player;

import java.util.HashMap;
import java.util.Map;
import racingcar.dao.entity.Player;

public class PlayerInMemoryDao implements PlayerDao {

    private static final Map<Long, Player> playerMemory = new HashMap<>();

    @Override
    public void insertPlayer(final Player player) {
        long lastKey = playerMemory.keySet().size();
        playerMemory.put(lastKey + 1, player);
    }

}
