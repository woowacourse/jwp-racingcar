package racingcar.controller.dto;

public class GameInfoRequest {

    private String names;
    private int count;

    private GameInfoRequest() {
    }

    public GameInfoRequest(String names, int count) {
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
