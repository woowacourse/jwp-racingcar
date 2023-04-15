package racingcar.dto;

import java.util.List;
import java.util.Objects;
import racingcar.domain.Car;

public class CarDto {

    private final String name;
    private final int position;
    private final boolean isWinner;

    private CarDto(final String name, final int position, final boolean isWinner) {
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public static CarDto of(final String name, final int position, final boolean isWinner) {
        return new CarDto(name, position, isWinner);
    }

    public static CarDto from(Car car, List<String> winnerNames) {
        return new CarDto(car.getName(), car.getPosition(), winnerNames.contains(car.getName()));
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return isWinner;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarDto carDto = (CarDto) o;
        return position == carDto.position && isWinner == carDto.isWinner && Objects.equals(name, carDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, isWinner);
    }
}
