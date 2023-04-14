package racingcar.dto;

public class GameInfo {

    private final String names;
    private final String count;

    public GameInfo(final String names, final String count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public String getCount() {
        return count;
    }
}
