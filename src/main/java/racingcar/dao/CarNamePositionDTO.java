package racingcar.dao;

import java.util.Objects;

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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CarNamePositionDTO that = (CarNamePositionDTO) o;
        return position == that.position && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }

    @Override
    public String toString() {
        return "CarNamePositionDTO{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
