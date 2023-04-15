package racingcar;

import java.util.List;

public class GameResultsDto {

    private final List<GameResultDto> results;

    public GameResultsDto(final List<GameResultDto> results) {
        this.results = results;
    }

    public List<GameResultDto> getResults() {
        return results;
    }
}
