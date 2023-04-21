package racingcar.service.dto;

import java.util.List;

public class GameHistoryDto {

    private final long gameId;
    private final List<String> winners;
    private final String name;
    private final int position;

    public GameHistoryDto(final long gameId, final List<String> winners, final String name, final int position) {
        this.gameId = gameId;
        this.winners = winners;
        this.name = name;
        this.position = position;
    }

    public long getGameId() {
        return gameId;
    }

    public List<String> getWinners() {
        return winners;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
