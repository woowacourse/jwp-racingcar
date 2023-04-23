package racingcar.dao.dto;

public class RacingGameDto {

    private final int id;
    private final String winners;

    public RacingGameDto(final int id, final String winners) {
        this.id = id;
        this.winners = winners;
    }

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }
}
