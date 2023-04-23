package racingcar.dao.car;

import java.util.concurrent.atomic.AtomicInteger;

public class CarEntity {

    private static final AtomicInteger autoIncrementId = new AtomicInteger(0);

    private final int gameId;
    private final int id;
    private final String name;
    private final int position;
    private final boolean isWinner;

    public CarEntity(final int gameId, final String name, final int position, final boolean isWinner) {
        this.gameId = gameId;
        this.id = autoIncrementId.incrementAndGet();
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public String getName() {
        return name;
    }

}
