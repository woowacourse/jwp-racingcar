package racingcar.dao;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/20
 */
public class RacingGameEntity {

    private int id;
    private final int trialCount;

    public RacingGameEntity(final int trialCount) {
        this.trialCount = trialCount;
    }

    public RacingGameEntity(final int id, final int trialCount) {
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
