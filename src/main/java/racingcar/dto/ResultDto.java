package racingcar.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.Result;

public class ResultDto {

    private final List<CarDto> winners;
    private final List<CarDto> cars;

    private ResultDto(final List<CarDto> winners, final List<CarDto> cars) {
        this.winners = winners;
        this.cars = cars;
    }

    public static ResultDto from(final Result result) {
        return new ResultDto(convertCarDtos(result.winners()),
                convertCarDtos(result.cars()));
    }

    private static List<CarDto> convertCarDtos(final List<Car> result) {
        return result.stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
    }

    public List<CarDto> getWinners() {
        return Collections.unmodifiableList(winners);
    }

    public List<CarDto> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
