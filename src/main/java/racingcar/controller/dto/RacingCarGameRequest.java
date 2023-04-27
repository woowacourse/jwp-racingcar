package racingcar.controller.dto;

public class RacingCarGameRequest {

    private String names;
    private int count;

    private RacingCarGameRequest() {
    }

    public RacingCarGameRequest(final String names, final int count) {
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
