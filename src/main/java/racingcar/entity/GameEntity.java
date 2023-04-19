package racingcar.entity;

public class GameEntity {

    private final Long id;
    private final String winners;

    public GameEntity(final Long id, final String winners) {
        this.id = id;
        this.winners = winners;
    }

    public Long getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }
}
