package racingcar.dao.entity;

public class WinnerEntity {

    private int carId;
    private int gameId;

    public WinnerEntity(final int carId, final int gameId) {
        this.carId = carId;
        this.gameId = gameId;
    }

    public int getCarId() {
        return carId;
    }

    public int getGameId() {
        return gameId;
    }
}
