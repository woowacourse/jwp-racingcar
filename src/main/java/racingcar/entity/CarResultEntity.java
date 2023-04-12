package racingcar.entity;

public class CarResultEntity {
    private final long id;
    private final long playResultId;
    private final String name;
    private final int position;

    private CarResultEntity(long id, long playResultId, String name, int position) {
        this.id = id;
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
    }

    public static CarResultEntity of(long resultId, String name, int position) {
        return new CarResultEntity(0, resultId, name, position);
    }

    public static CarResultEntity of(long id, long resultId, String name, int position) {
        return new CarResultEntity(id, resultId, name, position);
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
