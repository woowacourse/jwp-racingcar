package racingcar.Entity;

import java.time.LocalDateTime;
import java.util.List;

public class Game {

    private static final String DELIMITER = ",";

    private final Integer id;
    private final String winners;
    private final int trial;
    private final LocalDateTime createdAt;


    private Game(final Integer id, final String winners, final int trial, final LocalDateTime createdAt) {
        this.id = id;
        this.winners = winners;
        this.trial = trial;
        this.createdAt = createdAt;
    }

    public static Game of(final List<String> winners, final int trial) {
        final String transFromWinners = String.join(DELIMITER, winners);
        return new Game(null, transFromWinners, trial, null);
    }

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public int getTrial() {
        return trial;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
