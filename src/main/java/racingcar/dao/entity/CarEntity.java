package racingcar.dao.entity;

public class CarEntity {
    private final int playResultId;
    private final String name;
    private final int position;

    public CarEntity(int playResultId, String name, int position) {
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
    }

    public int getPlayResultId() {
        return playResultId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
