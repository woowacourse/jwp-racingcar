package racingcar.dao;

import java.util.List;
import java.util.Optional;

public class InMemoryGameDao implements GameDao {
    @Override
    public int insert(String winners, Integer count) {
        return 0;
    }

    @Override
    public List<Integer> findAllIds() {
        return null;
    }

    @Override
    public String findWinners(int gameId) {
        return null;
    }
}
