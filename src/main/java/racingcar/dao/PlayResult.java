package racingcar.dao;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/11
 */
public class PlayResult {
    private int id;
    private final String winners;
    private final int trialCount;

    public PlayResult(final String winners, final int trialCount) {
        this.winners = winners;
        this.trialCount = trialCount;
    }

    public PlayResult(final int id, final String winners, final int trialCount) {
        this.id = id;
        this.winners = winners;
        this.trialCount = trialCount;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public int getTrialCount() {
        return trialCount;
    }

    @Override
    public String toString() {
        return "PlayResult{" +
                "id=" + id +
                ", winners='" + winners + '\'' +
                ", trialCount=" + trialCount +
                '}';
    }
}
