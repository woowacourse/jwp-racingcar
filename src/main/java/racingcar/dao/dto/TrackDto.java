package racingcar.dao.dto;

public class TrackDto {
    private final int trialTimes;

    public TrackDto(final int trialTimes) {
        this.trialTimes = trialTimes;
    }

    public int getTrialTimes() {
        return trialTimes;
    }
}
