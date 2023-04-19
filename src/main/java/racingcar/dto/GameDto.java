package racingcar.dto;

import java.util.List;

public class GameDto {

    private final List<String> names;
    private final int trialCount;

    public GameDto(final List<String> names, final int trialCount) {
        this.names = names;
        this.trialCount = trialCount;
    }

    public GameDto(final GameRequestDto gameRequestDto) {
        this(NamesDto.of(gameRequestDto.getNames()).getNames(), gameRequestDto.getCount());
    }

    public List<String> getNames() {
        return names;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
