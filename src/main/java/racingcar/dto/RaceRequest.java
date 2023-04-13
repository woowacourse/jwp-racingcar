package racingcar.dto;

public class RaceRequest {

    private String names;
    private int count;

    public RaceRequest() {
    }

    public RaceRequest(final String names, final int count) {
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
