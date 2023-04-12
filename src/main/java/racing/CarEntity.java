package racing;

import racing.domain.Car;

public class CarEntity {
    private final Long gameId;
    private final String carName;
    private final int step;
    private final boolean isWinner;

    private CarEntity(Long gameId, String carName, int step, boolean isWinner) {
        this.gameId = gameId;
        this.carName = carName;
        this.step = step;
        this.isWinner = isWinner;
    }

    public static CarEntity of(Long gameId, Car car, boolean isWinner) {
        return new CarEntity(gameId, car.getName(), car.getStep(), isWinner);
    }

    public Long getGameId() {
        return gameId;
    }

    public String getCarName() {
        return carName;
    }

    public int getStep() {
        return step;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
