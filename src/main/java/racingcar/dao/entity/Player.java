package racingcar.dao.entity;

import java.util.Optional;
import racingcar.domain.Car;

public class Player {

    private final Long playerId;
    private final String name;
    private final int position;
    private final Long gameId;

    public Player(String name, int position, Long gameId) {
        this.playerId = null;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
    }

    public Player(Car car) {
        this.playerId = null;
        this.name = car.getName();
        this.position = car.getPosition();
        this.gameId = null;
    }

    public Optional<Long> getPlayerId() {
        return Optional.ofNullable(playerId);
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
}
