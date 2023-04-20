package racingcar.entity;

public class Player {

    private final long id;
    private final long playResultId;
    private final String name;
    private final int position;
    private final boolean winner;

    public Player(final long id, final long playResultId, final String name, final int position, final boolean winner) {
        this.id = id;
        this.playResultId = playResultId;
        this.name = name;
        this.position = position;
        this.winner = winner;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public long getPlayResultId() {
        return playResultId;
    }

    public boolean isWinner() {
        return winner;
    }
}
