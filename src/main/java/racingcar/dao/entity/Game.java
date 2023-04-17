package racingcar.dao.entity;

public class Game {

    private final Long gameId;
    private final int playCount;
    private final String winners;

    public Game(Long gameId, int playCount, String winners) {
        this.gameId = gameId;
        this.playCount = playCount;
        this.winners = winners;
    }

    public Long getGameId() {
        return gameId;
    }

    public int getPlayCount() {
        return playCount;
    }

    public String getWinners() {
        return winners;
    }

}
