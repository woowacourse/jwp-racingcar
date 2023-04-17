package racingcar.dao.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryGameDao implements GameDao {
    private long serial = 0L;
    private final Map<Long, Integer> gameTable = new HashMap<>();

    @Override
    public long saveGame(int trialCount) {
        serial += 1;
        gameTable.put(serial, trialCount);
        return serial;
    }

    @Override
    public List<Long> getGameIds() {
        return new ArrayList<>(gameTable.keySet());
    }
}
