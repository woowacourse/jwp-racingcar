package racingcar.repository.mapper;

import java.time.LocalDateTime;

public class RacingGameInfo {
    private final int id;
    private final String winners;
    private final LocalDateTime createdAt;
    private final int trial;

    public RacingGameInfo(final int id, final String winners, final LocalDateTime createdAt, final int trial) {
        this.id = id;
        this.winners = winners;
        this.createdAt = createdAt;
        this.trial = trial;
    }
}
