package racingcar.dao.dto;

public class GameStateDto {

    private final Integer id;
    private final Integer initialTrialCount;
    private final Integer remainingTrialCount;

    public GameStateDto(Integer id, Integer initialTrialCount, Integer remainingTrialCount) {
        this.id = id;
        this.initialTrialCount = initialTrialCount;
        this.remainingTrialCount = remainingTrialCount;
    }

    public Integer getId() {
        return id;
    }

    public Integer getInitialTrialCount() {
        return initialTrialCount;
    }

    public Integer getRemainingTrialCount() {
        return remainingTrialCount;
    }
}
