package racingcar.dao.entity;

import racingcar.domain.Car;

public class CarEntity {

    private final Integer carId;
    private final String name;
    private final int position;
    private final Integer gameId;

    public CarEntity(final String name, final int position, final Integer gameId) {
        this(null, name, position, gameId);
    }

    public CarEntity(final Integer carId, final String name, final int position, final Integer gameId) {
        this.carId = carId;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public static CarEntity fromDomain(final Car car, final Integer gameId) {
        return new CarEntity(car.getCarName(), car.getPosition(), gameId);
    }

    public Car toDomain() {
        return new Car(name, position, carId);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Integer getCarId() {
        return carId;
    }

    public Integer getGameId() {
        return gameId;
    }
}
