package racingcar.dto;

public class GameWinnerDto {
    private final int gameId;
    private final String winners;

    public GameWinnerDto(int gameId, String winners) {
        this.gameId = gameId;
        this.winners = winners;
    }

    public int getGameId() {
        return gameId;
    }

    public String getWinners() {
        return winners;
    }

    @Override
    public String toString() {
        return "GameWinnerDto{" +
                "gameId=" + gameId +
                ", winners='" + winners + '\'' +
                '}';
    }
}
