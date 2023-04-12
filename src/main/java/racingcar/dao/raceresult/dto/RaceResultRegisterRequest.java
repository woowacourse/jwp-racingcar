package racingcar.dao.raceresult.dto;

public class RaceResultRegisterRequest {

    private final int trialCount;
    private final String winners;

    public RaceResultRegisterRequest(final int trialCount, final String winners) {
        this.trialCount = trialCount;
        this.winners = winners;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }
}
