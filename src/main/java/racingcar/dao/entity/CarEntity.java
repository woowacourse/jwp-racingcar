package racingcar.dao.entity;

public class CarEntity {

    private int carId;
    private String name;
    private int position;
    private int gameId;

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
