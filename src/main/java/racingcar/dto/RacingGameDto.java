package racingcar.dto;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/20
 */
public class RacingGameDto {

    private int id;
    private final int trialCount;

    private RacingGameDto(final int trialCount) {
        this.trialCount = trialCount;
    }

    private RacingGameDto(final int id, final int trialCount) {
        this.id = id;
        this.trialCount = trialCount;
    }

    public static RacingGameDto from(final int trialCount) {
        return new RacingGameDto(trialCount);
    }

    public static RacingGameDto of(final int id, final int trialCount) {
        return new RacingGameDto(id, trialCount);
    }

    public int getTrialCount() {
        return trialCount;
    }

    public int getId() {
        return id;
    }

}
