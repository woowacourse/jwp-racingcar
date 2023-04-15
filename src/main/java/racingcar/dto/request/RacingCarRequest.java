package racingcar.dto.request;

public class RacingCarRequest {

    private String names;
    private Integer count;

    public RacingCarRequest() {

    }

    public RacingCarRequest(final String names, final Integer count) {
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
