package racingcar.controller.dto;

public class NamesAndCountRequest {
    private String names;
    private int count;

    public NamesAndCountRequest() {
    }

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
