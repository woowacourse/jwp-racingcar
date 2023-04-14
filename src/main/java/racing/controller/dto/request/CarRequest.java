package racing.controller.dto.request;

import racing.domain.Car;

public class CarRequest {
    private final Long gameId;
    private final String carName;
    private final int step;
    private final boolean isWinner;

    private CarRequest(Long gameId, String carName, int step, boolean isWinner) {
        this.gameId = gameId;
        this.carName = carName;
        this.step = step;
        this.isWinner = isWinner;
    }

    public static CarRequest of(Long gameId, Car car, boolean isWinner) {
        return new CarRequest(gameId, car.getName(), car.getStep(), isWinner);
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
