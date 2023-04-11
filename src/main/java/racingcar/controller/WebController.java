package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.dto.VehicleDto;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.Vehicle;
import racingcar.util.CarNameValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebController {
    @PostMapping("/plays")
    public PlayResponse plays(@RequestBody final PlayRequest playRequest) {
        final String DELIMITER = ",";

        String names = playRequest.getNames();
        int count = playRequest.getCount();

        List<String> carNames = Arrays.asList(names.split(DELIMITER, -1));
        List<String> trimCarNames = carNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());

        CarNameValidator carNameValidator = new CarNameValidator();
        carNameValidator.validate(trimCarNames);

        Cars cars = new Cars(trimCarNames
                .stream()
                .map(Car::new)
                .collect(Collectors.toUnmodifiableList())
        );

        for (int i = 0; i < count; i++) {
            cars.moveAll();
        }

        List<String> winners = cars.getWinner().stream()
                .map(Vehicle::getName)
                .collect(Collectors.toList());

        List<VehicleDto> racingCars = cars.getCars()
                .stream()
                .map(car -> new VehicleDto(car.getName(), car.getDistance()))
                .collect(Collectors.toList());

        return new PlayResponse(winners, racingCars);
    }
}
