package racingcar.dao.entity;

public class GameEntity {
    private final int id;
    private final int count;
    private final String winners;

    public GameEntity(int id, int count, String winners) {
        this.id = id;
        this.count = count;
        this.winners = winners;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public String getWinners() {
        return winners;
    }

}
