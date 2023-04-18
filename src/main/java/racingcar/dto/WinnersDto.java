package racingcar.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WinnersDto {
    private final int gameIds;
    private final List<String> winners;

    public WinnersDto(int gameIds, List<String> winners) {
        this.gameIds = gameIds;
        this.winners = winners;
    }

    public int getGameIds() {
        return gameIds;
    }

    public List<String> getWinners() {
        return winners;
    }
}
