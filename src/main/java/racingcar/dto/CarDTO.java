package racingcar.dto;

public class CarDTO {

    private String name;
    private int position;

    public CarDTO() {
    }

    public CarDTO(final String name, final int position) {
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
