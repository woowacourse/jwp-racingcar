package racingcar.dao;

public class CarEntity {

    private int carId;
    private final String name;
    private final int position;
    private final int gameId;

    public CarEntity(final String name, final int position, final int gameId) {
        this.name = name;
        this.position = position;
        this.gameId = gameId;
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
