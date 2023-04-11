package racingcar.entity;

public class CarEntity {
    private final long id;
    private final long playResultId;
    private final String name;
    private final int position;

    private CarEntity(long id, long playResultId, String name, int position) {
        this.id = id;
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
    }

    public static CarEntity of(long resultId, String name, int position) {
        return new CarEntity(0, resultId, name, position);
    }

    public static CarEntity of(long id, long resultId, String name, int position) {
        return new CarEntity(id, resultId, name, position);
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
