package racingcar.controller.dto;

public class PlayRequest {

    private final String names;
    private final int count;

    public PlayRequest(String names, int count) {
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
