package racingcar.model;

public class PlayRequest {
    private final String names;
    private final int count;

    PlayRequest() {
        this.names = "";
        this.count = 0;
    }

    public PlayRequest(final String names, final int count) {
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
