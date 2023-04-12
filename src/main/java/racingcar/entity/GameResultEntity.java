package racingcar.entity;

public class GameResultEntity {
    private final int tryCount;

    public GameResultEntity(int tryCount) {
        this.tryCount = tryCount;
    }

    public int getTryCount() {
        return tryCount;
    }
}
