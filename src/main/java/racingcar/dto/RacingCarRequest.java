package racingcar.dto;

public class RacingCarRequest {
    private final String names;
    private final int count;

    public RacingCarRequest(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
