package racingcar.entity;

public class Game {

    private final long id;
    private final int trialCount;
    private final String winners;

    public Game(final long id, final int trialCount, final String winners) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
    }

    public long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }
}
