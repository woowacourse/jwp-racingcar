package racingcar.dao.entity;

public class GameEntity {
    private final int id;
    private final int count;

    public GameEntity(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }
}
