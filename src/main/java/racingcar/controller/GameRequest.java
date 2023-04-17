package racingcar.controller;

public class GameRequest {

    private String names;
    private int count;

    public GameRequest() {
    }

    public GameRequest(String names, int count) {
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
