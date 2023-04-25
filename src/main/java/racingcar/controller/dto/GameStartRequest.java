package racingcar.controller.dto;

public class GameStartRequest {
    private String names;
    private int count;

    public GameStartRequest() {
    }

    public GameStartRequest(final String names, final int count) {
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
