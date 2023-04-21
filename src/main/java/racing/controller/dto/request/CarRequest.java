package racing.controller.dto.request;

import racing.domain.Car;

public class CarRequest {
    private final Long gameId;
    private final String carName;
    private final int position;
    private final boolean isWinner;

    private CarRequest(Long gameId, String carName, int position, boolean isWinner) {
        this.gameId = gameId;
        this.carName = carName;
        this.position = position;
        this.isWinner = isWinner;
    }

    public static CarRequest of(Long gameId, Car car, boolean isWinner) {
        return new CarRequest(gameId, car.getName(), car.getPosition(), isWinner);
    }

    public Long getGameId() {
        return gameId;
    }

    public String getCarName() {
        return carName;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
