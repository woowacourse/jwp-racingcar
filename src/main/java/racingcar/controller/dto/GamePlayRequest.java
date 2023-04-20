package racingcar.controller.dto;

public class GamePlayRequest {

    private final String names;
    private final int count;

    public GamePlayRequest(final String names, final int count) {
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
