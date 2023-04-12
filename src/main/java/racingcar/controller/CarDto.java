package racingcar.controller;

public class CarDto {

    private final String name;
    private final String position;

    public CarDto(final String name, final String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}
