package racing.persist.car;

import java.util.Objects;
import racing.domain.Car;
import racing.domain.CarName;

public class CarEntity {
    private final String carName;
    private final int step;
    private final boolean isWinner;
    private final Long gameId;

    private CarEntity(String carName, int step, boolean isWinner, Long gameId) {
        this.carName = carName;
        this.step = step;
        this.isWinner = isWinner;
        this.gameId = gameId;
    }

    public static CarEntity of(Long gameId, Car car, boolean isWinner) {
        return new CarEntity(car.getName(), car.getStep(), isWinner, gameId);
    }

    public static CarEntity of(Long gameId, String carName, int step, boolean isWinner) {
        return new CarEntity(carName, step, isWinner, gameId);
    }

    public Car toCar() {
        return new Car(new CarName(carName), step);
    }

    public boolean matchGame(Long gameId) {
        return Objects.equals(this.gameId, gameId);
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
