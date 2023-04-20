package racingcar.dao.mapper;

public class RacingGameDtoMapper {

    private final int id;
    private final String winners;

    public RacingGameDtoMapper(final int id, final String winners) {
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
