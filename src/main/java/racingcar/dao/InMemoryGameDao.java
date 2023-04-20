package racingcar.dao;

import java.util.Optional;

public class InMemoryGameDao implements GameDao {
    @Override
    public int insert(String winners, Integer count) {
        return 0;
    }

    @Override
    public Optional<Integer> findLastId() {
        return Optional.empty();
    }

    @Override
    public String findWinners(int gameId) {
        return null;
    }
}
