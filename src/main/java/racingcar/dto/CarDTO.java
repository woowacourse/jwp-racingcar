package racingcar.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CarDTO carDTO = (CarDTO) o;
        return position == carDTO.position && Objects.equals(name, carDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "name='" + name + '\'' +
                ", position=" + position +
                '}';
    }
}
