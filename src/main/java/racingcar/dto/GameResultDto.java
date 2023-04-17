package racingcar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.stream.Collectors;

public class GameResultDto {

    private static final String SERIALIZE_DELIMITER = ",";

    private final String winners;
    private final int playCount;
    @JsonProperty(value = "racingCars")
    private final List<PlayerDto> players;

    public GameResultDto(final int playCount, final List<PlayerDto> players) {
        this.winners = serializeWinners(players);
        this.playCount = playCount;
        this.players = players;
    }

    private String serializeWinners(final List<PlayerDto> players) {
        return players.stream()
                .filter(PlayerDto::isWinner)
                .map(PlayerDto::getName)
                .collect(Collectors.joining(SERIALIZE_DELIMITER));
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
