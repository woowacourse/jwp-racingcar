package racingcar.dto;

public class GamePlayerJoinDto {

    private final Long gameId;
    private final String winner;
    private final String playerName;
    private final int position;
    private final Long playerId;

    public GamePlayerJoinDto(final Long gameId, final String winner, final String playerName, final int position, final Long playerId) {
        this.gameId = gameId;
        this.winner = winner;
        this.playerName = playerName;
        this.position = position;
        this.playerId = playerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getWinner() {
        return winner;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPosition() {
        return position;
    }

    public Long getPlayerId() {
        return playerId;
    }
}
