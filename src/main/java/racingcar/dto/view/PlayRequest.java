package racingcar.dto.view;

public class PlayRequest {
    private String names;
    private String count;

    private PlayRequest() {
    }

    public PlayRequest(String names, String count) {
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
