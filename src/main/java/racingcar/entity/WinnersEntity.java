package racingcar.entity;

public class WinnersEntity {

    private final int gameId;
    private final int carId;

    public WinnersEntity(int gameId, int carId) {
        this.gameId = gameId;
        this.carId = carId;
    }

    public int getGameId() {
        return gameId;
    }

    public int getCarId() {
        return carId;
    }

    public static class Builder {

        private int gameId;
        private int carId;

        public Builder gameId(int id) {
            this.gameId = id;
            return this;
        }

        public Builder carId(int count) {
            this.carId = count;
            return this;
        }

        public WinnersEntity build() {
            return new WinnersEntity(gameId, carId);
        }

    }

}
