package racingcar.persistence.entity;

import java.util.Date;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;

public class GameResultEntity {

    private static final int NONE_ID = 0;

    private final long id;
    private final int trialCount;
    private final String winners;
    private final Date createdAt;

    public GameResultEntity(
            final long id,
            final int trialCount,
            final String winners,
            final Date createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public static GameResultEntity createToSave(final RacingGame racingGame, final int trialCount) {
        return new GameResultEntity(
                NONE_ID,
                trialCount,
                racingGame.getWinners().stream()
                        .map(Car::getCarName)
                        .collect(Collectors.joining(",")),
                null);
    }

    public long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
