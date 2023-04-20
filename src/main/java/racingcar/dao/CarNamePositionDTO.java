package racingcar.dao;

public class CarNamePositionDTO {

    private final String name;
    private final int position;

    public CarNamePositionDTO(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "CarNamePositionDTO{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
