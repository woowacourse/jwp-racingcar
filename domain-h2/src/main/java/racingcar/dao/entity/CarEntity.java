package racingcar.dao.entity;

import racingcar.domain.Car;

public class CarEntity {

    private final CarId carId;
    private final String name;
    private final int position;
    private final GameId gameId;

    public CarEntity(final String name, final int position, final Integer gameId) {
        this(null, name, position, gameId);
    }

    public CarEntity(final Integer carId, final String name, final int position, final Integer gameId) {
        this(new CarId(carId), name, position, new GameId(gameId));
    }

    public CarEntity(final CarId carId, final String name, final int position, final GameId gameId) {
        this.carId = carId;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public static CarEntity fromDomain(final Car car, final GameId gameId) {
        return new CarEntity(car.getCarName(), car.getPosition(), gameId.getValue());
    }

    public Car toDomain() {
        return new Car(name, position, carId.getValue());
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "carId=" + carId +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", gameId=" + gameId +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public CarId getCarId() {
        return carId;
    }

    public GameId getGameId() {
        return gameId;
    }
}
