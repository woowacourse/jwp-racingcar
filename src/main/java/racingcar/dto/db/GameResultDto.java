package racingcar.dto.db;

public final class GameResultDto {

    private final int tryCount;


    public GameResultDto(final int tryCount) {
        this.tryCount = tryCount;
    }

    public int getTryCount() {
        return tryCount;
    }
}
