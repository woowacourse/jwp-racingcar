package racingcar.dao.entity;

public class WinnerEntity {

    private final WinnerId winnerId;
    private final GameId gameId;
    private final CarId carId;

    public WinnerEntity(final Integer gameId, final Integer carId) {
        this(null, gameId, carId);
    }

    public WinnerEntity(final Integer winnerId, final Integer gameId, final Integer carId) {
        this(new WinnerId(winnerId), new GameId(gameId), new CarId(carId));
    }

    public WinnerEntity(final WinnerId winnerId, final GameId gameId, final CarId carId) {
        this.winnerId = winnerId;
        this.gameId = gameId;
        this.carId = carId;
    }

    public GameId getGameId() {
        return gameId;
    }

    public CarId getCarId() {
        return carId;
    }

    public WinnerId getWinnerId() {
        return winnerId;
    }
}
