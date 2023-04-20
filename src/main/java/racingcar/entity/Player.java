package racingcar.entity;

import racingcar.domain.Car;

public class Player {

    private Integer id;
    private String name;
    private int position;
    private int gameId;
    private boolean isWinner;

    public Player(Integer id, String name, int position, int gameId, boolean isWinner) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
        this.isWinner = isWinner;
    }

    public static Player of(Car car, int gameId, boolean isWinner) {
        return new Player(null, car.getName(), car.getDrivenDistance(), gameId, isWinner);
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
