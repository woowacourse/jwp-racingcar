package racingcar.service;

public class CarEntity {

    private final int playResultId;
    private final String name;
    private final int position;
    private int id;

    public CarEntity(final int playResultId, final String name, final int position) {
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
    }

    public CarEntity(final int id, final int playResultId, final String name, final int position) {
        this.id = id;
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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
