package racingcar.dao;

public class PlayerResult {

    private int id;
    private int playResultId;
    private String name;
    private int position;

    public PlayerResult(final int playResultId, final String name, final int position) {
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
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
