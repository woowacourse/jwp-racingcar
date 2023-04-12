package racingcar.controller.dto;

public class CarInfoDto {

    private String name;
    private int position;

    public CarInfoDto() {
    }

    public CarInfoDto(final String name, final int position) {
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
