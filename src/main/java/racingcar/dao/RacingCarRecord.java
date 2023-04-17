package racingcar.dao;

public class RacingCarRecord {

    private final long id;
    private final String name;
    private final int position;
    private final boolean isWinner;
    private final long historyId;

    public RacingCarRecord(long id, String name, int position, boolean isWinner, long historyId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
        this.historyId = historyId;
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

    public boolean isWinner() {
        return isWinner;
    }

    public long getHistoryId() {
        return historyId;
    }
}
