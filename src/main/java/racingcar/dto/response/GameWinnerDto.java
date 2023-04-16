package racingcar.dto.response;

public class GameWinnerDto {
    private final Long gameId;
    private final String winners;

    public GameWinnerDto(Long gameId, String winners) {
        this.gameId = gameId;
        this.winners = winners;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getWinners() {
        return winners;
    }
}
