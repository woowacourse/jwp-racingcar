package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dto.CarDto;
import racingcar.dto.GameDto;
import racingcar.model.Car;
import racingcar.model.Cars;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    public static final String DELIMITER = ",";

    public Cars play(final GameDto gameDto) {
        Cars cars = createCars(gameDto);

        int count = gameDto.getCount();

        for (int i = 0; i < count; i++) {
            cars.moveAll();
        }

        return cars;
    }

    private Cars createCars(final GameDto gameDto) {
        String names = gameDto.getNames();
        List<String> trimCarNames = Arrays.stream(names.split(DELIMITER, -1))
                .map(String::trim)
                .collect(Collectors.toList());

        return new Cars(trimCarNames.stream()
                .map(Car::new)
                .collect(Collectors.toUnmodifiableList()));
    }

    public List<CarDto> carsToDto(Cars cars){
        return cars.getCars().stream()
                .map(car -> new CarDto(car.getName(), car.getDistance()))
                .collect(Collectors.toList());
    }
}
