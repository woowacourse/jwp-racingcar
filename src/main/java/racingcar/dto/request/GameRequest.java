package racingcar.dto.request;

public class GameRequest {
    private final String names;
    private final int count;

    public GameRequest(String names, int count) {
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
