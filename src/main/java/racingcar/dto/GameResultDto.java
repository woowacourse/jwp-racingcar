package racingcar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GameResultDto {

    private static final String SERIALIZE_DELIMITER = ",";

    private final String winners;
    private final int playCount;
    @JsonProperty(value = "racingCars")
    private final List<PlayerDto> players;

    public GameResultDto(final List<String> winners, final int playCount, final List<PlayerDto> players) {
        this.winners = serializeWinners(winners);
        this.playCount = playCount;
        this.players = players;
    }

    private static String serializeWinners(final List<String> winners) {
        return String.join(SERIALIZE_DELIMITER, winners);
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public int getPlayCount() {
        return playCount;
    }
}
