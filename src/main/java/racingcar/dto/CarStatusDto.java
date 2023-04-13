package racingcar.dto;

public class CarStatusDto {

    private final String name;
    private final int position;

    public CarStatusDto(final String name, final int position) {
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
