package racingcar.dto;

import java.util.List;

public class NamesAndCountRequest {

    public static final String SEPARATOR = ",";

    private String names;
    private int count;

    public NamesAndCountRequest() {}

    public NamesAndCountRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getNames() {
        return List.of(names.split(SEPARATOR));
    }

    public int getCount() {
        return count;
    }
}
