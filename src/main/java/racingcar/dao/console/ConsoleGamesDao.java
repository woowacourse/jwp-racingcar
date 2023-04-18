package racingcar.dao.console;

import racingcar.dao.GamesDao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleGamesDao implements GamesDao {

    private final Map<Integer, Integer> games;
    private int index;

    public ConsoleGamesDao() {
        this.games = new HashMap<>();
        this.index = 1;
    }

    @Override
    public int insert(final int trialCount) {
        games.put(index, trialCount);
        index++;
        return index;
    }

    @Override
    public List<Integer> findAllGameIds() {
        return games.keySet().stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toUnmodifiableList());
    }
}
