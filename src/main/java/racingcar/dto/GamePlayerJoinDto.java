package racingcar.dto;

public class GamePlayerJoinDto {

    private final Long gameId;
    private final Integer playCount;
    private final String playerName;
    private final int position;
    private final boolean isWinner;
    private final Long playerId;

    public GamePlayerJoinDto(final Long gameId, final Integer playCount, final String playerName, final int position, final boolean isWinner, final Long playerId) {
        this.gameId = gameId;
        this.playCount = playCount;
        this.playerName = playerName;
        this.position = position;
        this.isWinner = isWinner;
        this.playerId = playerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public int getPlaycount() {
        return playCount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public Long getPlayerId() {
        return playerId;
    }
}
