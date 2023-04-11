package racingcar.dto;

public class NamesAndCountRequest {
    private final String names;
    private final int count;

    public NamesAndCountRequest(final String names, final int count) {
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
