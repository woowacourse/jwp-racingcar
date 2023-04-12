package racingcar.controller;

public class TrackRequest {

    private String names;
    private String count;

    protected TrackRequest() {
    }

    public TrackRequest(final String names, final String count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public String getCount() {
        return count;
    }
}
