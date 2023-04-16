package racingcar.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import racingcar.domain.Car;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CarDto {

    private final String name;
    private final int position;

    public static CarDto from(final Car car) {
        return new CarDto(car.getName(), car.getDistance());
    }
}
