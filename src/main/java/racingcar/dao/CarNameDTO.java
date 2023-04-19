package racingcar.dao;

import java.util.Objects;

public class CarNameDTO {

    private final String name;

    public CarNameDTO(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CarNameDTO that = (CarNameDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CarNameDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
