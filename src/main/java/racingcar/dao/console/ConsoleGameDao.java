package racingcar.dao.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import racingcar.dao.GameDao;

public class ConsoleGameDao implements GameDao {

    private final Map<Integer, Integer> games = new HashMap<>();

    @Override
    public int insertGame(final int tryTimes) {
        int id = games.size() + 1;
        games.put(id, tryTimes);

        return id;
    }

    @Override
    public List<Integer> findAllGamesId() {
        return new ArrayList<>(games.keySet());
    }
}
