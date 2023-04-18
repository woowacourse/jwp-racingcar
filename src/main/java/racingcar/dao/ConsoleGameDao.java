package racingcar.dao;

import java.util.List;
import racingcar.domain.TryCount;

public final class ConsoleGameDao implements GameDao {
    @Override
    public Number insertGame(final TryCount tryCount) {
        return null;
    }

    @Override
    public List<Integer> selectGameIds() {
        return List.of();
    }
}
