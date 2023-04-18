package racingcar.dto;

public class gameInitializationRequest {

    private String names;
    private int count;

    public gameInitializationRequest(final String names, final int count) {
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
