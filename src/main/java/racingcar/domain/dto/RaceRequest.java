package racingcar.domain.dto;

public class RaceRequest {

    private final String names;
    private final int count;

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
