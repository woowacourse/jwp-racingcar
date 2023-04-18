package racingcar.dao.mapper;

import java.time.LocalDateTime;

public class RacingGameDtoMapper {

    private final int id;
    private final String winners;
    private final LocalDateTime createdAt;
    private final int trial;

    public RacingGameDtoMapper(final int id, final String winners, final LocalDateTime createdAt, final int trial) {
        this.id = id;
        this.winners = winners;
        this.createdAt = createdAt;
        this.trial = trial;
    }

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }
}
