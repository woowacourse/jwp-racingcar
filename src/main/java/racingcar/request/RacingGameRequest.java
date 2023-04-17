package racingcar.request;

public class RacingGameRequest {

    private String names;
    private int count;

    public RacingGameRequest() {
    }

    public RacingGameRequest(final String names, final int count) {
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
