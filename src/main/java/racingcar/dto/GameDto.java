package racingcar.dto;

public class GameDto {

    private final Long id;
    private final String winners;

    public GameDto(final Long id, final String winners) {
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
