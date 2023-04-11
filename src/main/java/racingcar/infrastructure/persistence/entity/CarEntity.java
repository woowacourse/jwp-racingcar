package racingcar.infrastructure.persistence.entity;

import racingcar.domain.Car;

public class CarEntity {

    private Long id;
    private String name;
    private int position;
    private Long gameId;

    public CarEntity(final Car car, final Long gameId) {
        this.name = car.getCarName();
        this.position = car.getPosition();
        this.gameId = gameId;
    }

    public CarEntity(final Long id, final String name, final int position, final Long gameId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
