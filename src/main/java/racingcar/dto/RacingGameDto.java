package racingcar.dto;

import racingcar.controller.ApplicationType;

import java.util.List;

public class RacingGameDto {

    private final List<String> names;
    private final int trialCount;
    private final ApplicationType applicationType;

    public RacingGameDto(List<String> names, int trialCount, ApplicationType applicationType) {
        this.names = names;
        this.trialCount = trialCount;
        this.applicationType = applicationType;
    }

    public List<String> getNames() {
        return names;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }
}
