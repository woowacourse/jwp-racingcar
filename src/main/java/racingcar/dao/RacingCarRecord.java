package racingcar.dao;

import racingcar.domain.cars.RacingCar;

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

    public RacingCar toDomain() {
        RacingCar racingCar = new RacingCar(id, name, position);
        return racingCar;
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
