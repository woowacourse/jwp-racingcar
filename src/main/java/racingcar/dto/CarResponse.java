package racingcar.dto;

public class CarResponse {
    private String name;
    private int position;

    public CarResponse(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
