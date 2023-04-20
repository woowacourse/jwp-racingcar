package racingcar.controller.dto;

public final class CarData {

    private final String name;
    private final int position;

    public CarData(final String name, final int position) {
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
