package racingcar.dto;

public final class GameResultDto {
    private final Long id;
    private final String winners;

    public GameResultDto(final Long id, final String winners) {
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
