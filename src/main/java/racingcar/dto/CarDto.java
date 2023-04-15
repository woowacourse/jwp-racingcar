package racingcar.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CarDto {

    private final String name;
    private final int position;

    private CarDto(final String name, final int position) {
        this.name = name;
        this.position = position;
    }

    public static List<CarDto> convert(List<JudgedCarDto> judgedCarDtos) {
        return judgedCarDtos.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
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
        CarDto that = (CarDto) o;
        return position == that.position && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}
