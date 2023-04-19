package racingcar.dto;

import java.util.List;
import racingcar.dto.GameResultDto;

public class GameResultsDto {

    private final List<GameResultDto> results;

    public GameResultsDto(final List<GameResultDto> results) {
        this.results = results;
    }

    public List<GameResultDto> getResults() {
        return results;
    }
}
