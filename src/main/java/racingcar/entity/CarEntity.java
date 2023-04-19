package racingcar.entity;

import racingcar.domain.Car;

public class CarEntity {
    private final Integer id;
    private final String name;
    private final int position;
    private final boolean winner;
    private final Integer gameId;

    public CarEntity(final String name, final int position, final boolean winner, final Integer gameId) {
        this(null, name, position, winner, gameId);
    }

    public CarEntity(
            final Integer id,
            final String name,
            final int position,
            final boolean winner,
            final Integer gameId
    ) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.winner = winner;
        this.gameId = gameId;
    }

    public static CarEntity of(final Car car, final boolean winner, final int gameId) {
        return new CarEntity(car.getName(), car.getPosition(), winner, gameId);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return winner;
    }

    public Integer getGameId() {
        return gameId;
    }
}
