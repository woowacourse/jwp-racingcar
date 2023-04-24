package racingcar.entity;

import java.util.Date;

public class GameEntity {

    private final long id;
    private final int moveCount;
    private final Date date;

    public GameEntity(long id, int move_count, Date date) {
        this.id = id;
        this.moveCount = move_count;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public Date getDate() {
        return date;
    }
}
