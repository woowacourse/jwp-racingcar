package racingcar.persistence.entity;

import racingcar.domain.Car;

public class PlayerResultEntity {

    private static final int NONE_ID = 0;

    private final long id;
    private final String name;
    private final int position;
    private final long gameResultId;

    public PlayerResultEntity(
            final long id,
            final String name,
            final int position,
            final long gameResultId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.gameResultId = gameResultId;
    }

    public static PlayerResultEntity createToSave(final Car car, final long gameResultId) {
        return new PlayerResultEntity(NONE_ID, car.getCarName(), car.getPosition(), gameResultId);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public long getGameResultId() {
        return gameResultId;
    }
}
