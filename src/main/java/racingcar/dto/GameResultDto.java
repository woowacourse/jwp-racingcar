package racingcar.dto;

public class GameResultDto {
    private final int trialCount;
    private final String winners;

    private GameResultDto(int trialCount, String winners) {
        this.trialCount = trialCount;
        this.winners = winners;
    }

    public static GameResultDto of(int trialCount, String winners) {
        return new GameResultDto(trialCount, winners);
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }
}
