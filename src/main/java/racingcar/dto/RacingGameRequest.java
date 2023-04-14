package racingcar.dto;

public class RacingGameRequest {
    private String names;
    private int count;

    public RacingGameRequest(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public RacingGameRequest() {
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
