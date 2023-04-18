package racingcar.dao;

public class CarEntity {

    private int carId;
    private String name;
    private int position;
    private int gameId;

    public CarEntity() {
    }

    public CarEntity(String name, int position, int gameId) {
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
