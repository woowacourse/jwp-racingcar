package racingcar.dao.game;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import racingcar.model.TryCount;

public class GameEntity {
    private static final AtomicInteger autoIncrementId = new AtomicInteger(0);

    private final int id;
    private final TryCount tryCount;
    private final LocalDateTime created_at;

    public GameEntity(final TryCount tryCount) {
        this.id = autoIncrementId.incrementAndGet();
        this.tryCount = tryCount;
        this.created_at = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

}
