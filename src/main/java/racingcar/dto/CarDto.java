package racingcar.dto;

public class CarDto {

    private final String name;
    private final int position;

    public CarDto(String name, int count) {
        this.name = name;
        this.position = count;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
