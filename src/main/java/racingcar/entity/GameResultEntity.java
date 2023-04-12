package racingcar.entity;

public class GameResultEntity {
    private int tryCount;

    public GameResultEntity(int tryCount) {
        this.tryCount = tryCount;
    }

    public int getTryCount() {
        return tryCount;
    }

    public void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }
}
