package racingcar.dao;

public class CarRecord {
    private final String name;
    private final int position;
    private final boolean isWinner;
    private final long historyId;

    public CarRecord(String name, int position, boolean isWinner, long historyId) {
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
        this.historyId = historyId;
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
