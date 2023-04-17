package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.RaceDto;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.NumberPicker;
import racingcar.dto.CarPositionDto;

public class ConsoleRacingGameService {

    private final NumberPicker numberPicker;

    public ConsoleRacingGameService(final NumberPicker numberPicker) {
        this.numberPicker = numberPicker;
    }

    public RaceDto race(final Cars cars, final Count count) {
        cars.race(count, numberPicker);

        return new RaceDto(toDto(cars.getCars()), toDto(cars.findWinner()));
    }

    private List<CarPositionDto> toDto(final List<Car> cars) {
            return cars.stream()
                    .map(CarPositionDto::new)
                    .collect(Collectors.toList());
    }
}
