package racingcar;

import java.util.List;
import racingcar.dto.NamesDto;

public class GameDto {

    private final List<String> names;
    private final int trialCount;

    public GameDto(final List<String> names, final int trialCount) {
        this.names = names;
        this.trialCount = trialCount;
    }

    public GameDto(final PlayRequestDto playRequestDto) {
        this(NamesDto.of(playRequestDto.getNames()).getNames(), playRequestDto.getCount());
    }

    public List<String> getNames() {
        return names;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
