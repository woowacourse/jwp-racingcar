package racingcar.dao.entity;

import racingcar.domain.Car;

public class CarEntity {


    private static final Integer DEFAULT_ID = null;

    private Integer carId;
    private String name;
    private int position;
    private int gameId;

    public CarEntity(final Integer carId, final String name, final int position, final int gameId) {
        this.carId = carId;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public static CarEntity from(final Car car, int gameId) {
        return new CarEntity(DEFAULT_ID, car.getName(), car.getPosition(), gameId);
    }

    public int getCarId() {
        return carId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getGameId() {
        return gameId;
    }
}
