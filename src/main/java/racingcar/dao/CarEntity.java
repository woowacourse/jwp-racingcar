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

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
