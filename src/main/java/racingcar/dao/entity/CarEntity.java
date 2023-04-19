package racingcar.dao.entity;

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

    public static CarEntity of(final String name, final int position, final int gameId) {
        return new CarEntity(DEFAULT_ID, name, position, gameId);
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
