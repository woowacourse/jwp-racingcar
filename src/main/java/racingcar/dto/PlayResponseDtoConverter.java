package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class PlayResponseDtoConverter {

    public static PlayResponseDto from(final List<Car> racingCars, final List<String> winnerNames) {
        return new PlayResponseDto(convertToDto(racingCars), String.join(", ", winnerNames));
    }

    private static List<CarDto> convertToDto(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }
}
