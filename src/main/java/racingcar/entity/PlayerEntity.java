package racingcar.entity;

import racingcar.domain.Car;

public class PlayerEntity {

    private final Integer id;
    private final String name;
    private final int position;
    private final int gameId;
    private final boolean isWinner;

    public PlayerEntity(Integer id, String name, int position, int gameId, boolean isWinner) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
        this.isWinner = isWinner;
    }

    public static PlayerEntity of(Car car, int gameId, boolean isWinner) {
        return new PlayerEntity(null, car.getName(), car.getDrivenDistance(), gameId, isWinner);
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

    public int getGameId() {
        return gameId;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
