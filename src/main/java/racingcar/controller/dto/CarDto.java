package racingcar.controller.dto;

public class CarDto {
    private String name;
    private int position;

    public CarDto() {
    }

    public CarDto(final String name, final int position) {
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
