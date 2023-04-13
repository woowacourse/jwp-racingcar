package racingcar.dto;

public final class RacingCarRequest {

    private final String names;
    private final int tryCount;

    public RacingCarRequest(final String names, final int count) {
        this.names = names;
        this.tryCount = count;
    }

    public String getNames() {
        return names;
    }

    public int getTryCount() {
        return tryCount;
    }
}
