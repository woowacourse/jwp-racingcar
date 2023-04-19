package racingcar.dao;

import java.util.List;
import racingcar.domain.TryCount;

public final class ConsoleGameDao implements GameDao {
    @Override
    public Number insertGame(final TryCount tryCount) {
        return new Number() {
            @Override
            public int intValue() {
                return 0;
            }

            @Override
            public long longValue() {
                return 0;
            }

            @Override
            public float floatValue() {
                return 0;
            }

            @Override
            public double doubleValue() {
                return 0;
            }
        };
    }

    @Override
    public List<Integer> selectGameIds() {
        return List.of();
    }
}
