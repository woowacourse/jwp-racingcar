package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

public class GameResultDto {

    private static final String SERIALIZE_DELIMITER = ",";

    private final String winners;
    private final int playCount;
    private final List<PlayerDto> racingCars;

    public GameResultDto(final int playCount, final List<PlayerDto> racingCars) {
        this.winners = serializeWinners(racingCars);
        this.playCount = playCount;
        this.racingCars = racingCars;
    }

    private String serializeWinners(final List<PlayerDto> racingCars) {
        return racingCars.stream()
                .filter(PlayerDto::isWinner)
                .map(PlayerDto::getName)
                .collect(Collectors.joining(SERIALIZE_DELIMITER));
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerDto> getRacingCars() {
        return racingCars;
    }

    public int getPlayCount() {
        return playCount;
    }
}
