package racing.web.controller.dto.request;

public class RacingGameInfoRequest {

    private String names;
    private int count;

    public RacingGameInfoRequest() {
    }

    public RacingGameInfoRequest(String names, int count) {
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
