package racingcar.dao.entity;

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
}
