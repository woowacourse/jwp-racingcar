package racingcar.dto;

public class GameHistoryDto {

    private final long gameId;
    private final int playCount;

    public GameHistoryDto(final long gameId, final int playCount) {
        this.gameId = gameId;
        this.playCount = playCount;
    }

    public long getGameId() {
        return gameId;
    }

    public int getPlayCount() {
        return playCount;
    }
}
