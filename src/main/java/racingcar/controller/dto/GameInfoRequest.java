package racingcar.controller.dto;

public class GameInfoRequest {

    private String names;
    private int count;

    private GameInfoRequest() {
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
