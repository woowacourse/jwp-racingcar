package racingcar.entity;

public class Game {

    private long id;
    private final int trialCount;
    private final String winners;

    public Game(final int trialCount, final String winners) {
        this.trialCount = trialCount;
        this.winners = winners;
    }

    public Game(final long id, final Game game) {
        this.id = id;
        this.trialCount = game.trialCount;
        this.winners = game.winners;
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
