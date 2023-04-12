package racingcar.dto;

public class GameRequest {

    public GameRequest() {
    }

    public GameRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    private String names;
    private int count;

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
