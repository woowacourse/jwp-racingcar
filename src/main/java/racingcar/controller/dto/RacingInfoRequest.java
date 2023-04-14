package racingcar.controller.dto;

public class RacingInfoRequest {
    private String names;
    private int count;

    public RacingInfoRequest() {
    }

    public RacingInfoRequest(final String names, final int count) {
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
