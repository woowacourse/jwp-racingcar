package racingcar.dto;

public class RacingRequest {

    private String names;
    private String count;

    public RacingRequest() {

    }

    public RacingRequest(String names, String count) {
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
