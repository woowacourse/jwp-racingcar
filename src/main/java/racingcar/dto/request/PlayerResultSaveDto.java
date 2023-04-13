package racingcar.dto.request;

import racingcar.domain.Car;

public class PlayerResultSaveDto {
    private final long gameId;
    private final String name;
    private final int finalPosition;

    public PlayerResultSaveDto(final long gameId, final Car car) {
        this.gameId = gameId;
        this.name = car.getCarName().getName();
        this.finalPosition = car.getCurrentPosition().getPosition();
    }

    public long getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getFinalPosition() {
        return finalPosition;
    }
}
