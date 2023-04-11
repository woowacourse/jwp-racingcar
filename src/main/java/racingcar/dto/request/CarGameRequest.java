package racingcar.dto.request;

public class CarGameRequest {
    private final String names;
    private final int count;

    public CarGameRequest(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getNames() {
        return names;
    }
}
