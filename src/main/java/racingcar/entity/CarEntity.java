package racingcar.entity;

public class CarEntity {
    private final long id;
    private final long playResultId;
    private final String name;
    private final int position;

    public CarEntity(long id, long playResultId, String name, int position) {
        this.id = id;
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public long getPlayResultId() {
        return playResultId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
