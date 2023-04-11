package racingcar.controller.dto;

public class CarStatusResponse {

    private final String name;
    private final int position;

    public CarStatusResponse(final String name, final int position) {
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
