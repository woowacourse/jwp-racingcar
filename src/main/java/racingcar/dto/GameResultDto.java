package racingcar.dto;

public class GameResultDto {
    private final int trialCount;
    private final String winners;

    public GameResultDto(int trialCount, String winners) {
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
