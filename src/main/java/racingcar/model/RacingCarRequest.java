package racingcar.model;

public class RacingCarRequest {
    private final String names;
    private final int count;

    public RacingCarRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getNames() {
        return names;
    }
}
