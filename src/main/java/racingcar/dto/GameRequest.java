package racingcar.dto;


public class GameRequest {

    private final String names;
    private final Integer count;

    public GameRequest(final String names, final Integer count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public Integer getCount() {
        return count;
    }
}
