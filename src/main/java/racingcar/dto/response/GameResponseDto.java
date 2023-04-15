package racingcar.dto.response;


import racingcar.dto.PlayerResultDto;

import java.util.List;

public class GameResponseDto {
    private final String winners;
    private final List<PlayerResultDto> playerResults;

    public GameResponseDto(String winners, List<PlayerResultDto> playerResults) {
        this.winners = winners;
        this.playerResults = playerResults;
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResultDto> getPlayerResults() {
        return playerResults;
    }
}
