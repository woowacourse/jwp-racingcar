package racingcar.dao.entity;

import racingcar.domain.Car;

public class WinnerEntity {

    private final Integer winnerId;
    private final Integer gameId;
    private final Integer carId;

    public WinnerEntity(final Integer gameId, final Integer carId) {
        this(null, gameId, carId);
    }

    public WinnerEntity(final Integer winnerId, final Integer gameId, final Integer carId) {
        this.winnerId = winnerId;
        this.gameId = gameId;
        this.carId = carId;
    }

    public static WinnerEntity fromDomain(final Car car, final Integer gameId) {
        return new WinnerEntity(gameId, car.getCarId());
    }

    public int getGameId() {
        return gameId;
    }

    public int getCarId() {
        return carId;
    }

    public int getWinnerId() {
        return winnerId;
    }
}
