package racingcar.model;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/19
 */
public class RacingGame {

    private int id;
    private final int trialCount;

    public RacingGame(final int trialCount) {
        this.trialCount = trialCount;
    }

    public RacingGame(final int id, final int trialCount) {
        this.id = id;
        this.trialCount = trialCount;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }
}
