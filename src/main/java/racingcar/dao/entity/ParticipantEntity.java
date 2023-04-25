package racingcar.dao.entity;

import java.util.Objects;

public class ParticipantEntity {

    private final Long gameId;
    private final Long playerId;
    private final Integer position;
    private final Boolean isWinner;

    public ParticipantEntity(final Long gameId, final Long playerId, final Integer position, final Boolean isWinner) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.position = position;
        this.isWinner = isWinner;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Integer getPosition() {
        return position;
    }

    public Boolean getWinner() {
        return isWinner;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ParticipantEntity that = (ParticipantEntity) o;
        return Objects.equals(gameId, that.gameId)
                && Objects.equals(playerId, that.playerId)
                && Objects.equals(position, that.position)
                && Objects.equals(isWinner, that.isWinner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, playerId, position, isWinner);
    }
}
