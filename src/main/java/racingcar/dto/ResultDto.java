package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Result;

public class ResultDto {

    private final List<CarDto> winners;
    private final List<CarDto> cars;

    private ResultDto(final List<CarDto> winners, final List<CarDto> cars) {
        this.winners = winners;
        this.cars = cars;
    }

    public static ResultDto from(final Result result) {
        List<CarDto> winners = result.getWinners().stream()
                .map(CarDto::from)
                .collect(Collectors.toList());

        List<CarDto> cars = result.getCars().stream()
                .map(CarDto::from)
                .collect(Collectors.toList());

        return new ResultDto(winners, cars);
    }

    public static ResultDto of(final List<CarDto> winners, final List<CarDto> cars) {
        return new ResultDto(winners, cars);
    }

    public List<CarDto> getWinners() {
        return winners;
    }

    public List<CarDto> getCars() {
        return cars;
    }
}
