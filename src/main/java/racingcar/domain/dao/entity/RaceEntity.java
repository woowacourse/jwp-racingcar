package racingcar.domain.dao.entity;

public class RaceEntity {

    private final Long id;
    private final int trialCount;
    private final String winners;

    public RaceEntity(Long id, int trialCount, String winners) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
    }

    public Long getId() {
        return id;
    }
}
