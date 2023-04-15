package racingcar.dto;

public class ParticipateDto {

    private final Long gameId;
    private final Long playerId;
    private final Integer position;
    private final Boolean isWinner;

    public ParticipateDto(final Long gameId, final Long playerId, final int position, final boolean isWinner) {
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

    public Boolean getIsWinner() {
        return isWinner;
    }
}
