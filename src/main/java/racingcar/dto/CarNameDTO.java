package racingcar.dto;

public class CarNameDTO {

    private final String name;

    public CarNameDTO(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CarNameDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
