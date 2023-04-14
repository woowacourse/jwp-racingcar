package racingcar.entity;

public final class GameResultEntity {

    private final int tryCount;

    public GameResultEntity(final int tryCount) {
        this.tryCount = tryCount;
    }

    public int getTryCount() {
        return tryCount;
    }
}
