package racingcar.dao.entity;

import java.util.Date;

public class GameEntity {

    private int id;
    private final int count;
    private final Date date;

    private GameEntity(final int id, final int count, final Date date) {
        this.id = id;
        this.count = count;
        this.date = date;
    }

    public static GameEntity create(final int count) {
        return new GameEntity(0, count, null);
    }

    public int getCount() {
        return count;
    }
}
