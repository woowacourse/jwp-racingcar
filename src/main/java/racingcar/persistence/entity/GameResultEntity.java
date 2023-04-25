package racingcar.persistence.entity;

import java.util.Date;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameResultEntity that = (GameResultEntity) o;
        return id == that.id && trialCount == that.trialCount && winners.equals(that.winners) && createdAt.equals(
                that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trialCount, winners, createdAt);
    }
}
