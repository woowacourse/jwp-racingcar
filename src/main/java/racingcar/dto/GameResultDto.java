package racingcar.dto;

public class GameResultDto {
    private final int trialCount;

    private GameResultDto(int trialCount) {
        this.trialCount = trialCount;
    }

    public static GameResultDto from(int trialCount) {
        return new GameResultDto(trialCount);
    }

    public int getTrialCount() {
        return trialCount;
    }
}
