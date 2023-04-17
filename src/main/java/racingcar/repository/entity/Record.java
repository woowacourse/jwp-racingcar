package racingcar.repository.entity;

public class Record {

    private final Long gameId;
    private final String name;
    private final int position;

    public Record(final Long gameId, final String name, final int position) {
        this.gameId = gameId;
        this.name = name;
        this.position = position;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
