package racingcar.dto;

import static java.util.stream.Collectors.toList;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import java.util.List;

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

    public static CarDto from(final RecordDto recordDto) {
        return new CarDto(recordDto.getPlayerName(), recordDto.getPosition());
    }

    public static List<CarDto> toListFrom(final Cars cars) {
        return cars.getCars()
                .stream()
                .map(CarDto::from)
                .collect(toList());
    }
}
