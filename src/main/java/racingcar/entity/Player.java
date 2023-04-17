package racingcar.entity;

import racingcar.domain.Car;

public class Player {

    private Integer id;
    private String name;
    private int position;
    private int gameId;

    private Player(Integer id, String name, int position, int gameId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public static Player of(Car car, int gameId) {
        return new Player(null, car.getName(), car.getDrivenDistance(), gameId);
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
}
