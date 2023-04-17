package racingcar.entity;

public class Player {

    private long id;
    private long playResultId;
    private String name;
    private int position;

    public Player(final long id, final long playResultId, final String name, final int position) {
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
