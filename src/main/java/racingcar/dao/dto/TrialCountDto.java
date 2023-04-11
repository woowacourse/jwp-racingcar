package racingcar.dao.dto;

public class TrialCountDto {

    private final int gameId;
    private final int trialCount;

    public TrialCountDto(final int gameId, final int trialCount) {
        this.gameId = gameId;
        this.trialCount = trialCount;
    }

    public int getGameId() {
        return gameId;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
