package racingcar;

import java.util.List;
import racingcar.dto.RacingCarNamesRequest;

public class RacingCarGameDto {

    private final List<String> names;
    private final int trialCount;

    public RacingCarGameDto(final List<String> names, final int trialCount) {
        this.names = names;
        this.trialCount = trialCount;
    }

    public RacingCarGameDto(final PlayRequest playRequest) {
        this(RacingCarNamesRequest.of(playRequest.getNames()).getNames(), playRequest.getCount());
    }

    public List<String> getNames() {
        return names;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
