package racingcar.infrastructure.persistence.entity;

import racingcar.domain.Winner;

public class WinnerEntity {

    private final String name;
    private final Long gameId;

    public WinnerEntity(final Winner winner, final Long gameId) {
        this.name = winner.getName();
        this.gameId = gameId;
    }

    public WinnerEntity(final String name, final Long gameId) {
        this.name = name;
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public Long getGameId() {
        return gameId;
    }
}
