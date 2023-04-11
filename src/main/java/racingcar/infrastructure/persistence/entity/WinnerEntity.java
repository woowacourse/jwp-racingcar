package racingcar.infrastructure.persistence.entity;

import racingcar.domain.Winner;

public class WinnerEntity {

    private Long id;
    private String name;
    private Long gameId;

    public WinnerEntity(final Winner winner, final Long gameId) {
        this.name = winner.getName();
        this.gameId = gameId;
    }

    public WinnerEntity(final Long id, final String name, final Long gameId) {
        this.id = id;
        this.name = name;
        this.gameId = gameId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setGameId(final Long gameId) {
        this.gameId = gameId;
    }
}
