package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
public class RacingService {

    private final CarNameValidator carNameValidator;

    @Autowired
    public RacingService(final CarNameValidator carNameValidator) {
        this.carNameValidator = carNameValidator;
    }

    public PlayResponse playRacing(final PlayRequest playRequest) {
        String names = playRequest.getNames();
        int count = playRequest.getCount();

        Cars cars = createCars(names);

        play(count, cars);

        List<String> winners = findWinnerName(cars);
        List<VehicleDto> racingCars = findAllVehicle(cars);

        return new PlayResponse(winners, racingCars);
    }

    private Cars createCars(final String names) {
        List<String> trimCarNames = Arrays.stream(names.split(",", -1))
                .map(String::trim)
                .collect(Collectors.toList());

        carNameValidator.validate(trimCarNames);

        return new Cars(trimCarNames.stream()
                .map(Car::new)
                .collect(Collectors.toUnmodifiableList()));
    }

    private void play(final int count, final Cars cars) {
        for (int i = 0; i < count; i++) {
            cars.moveAll();
        }
    }

    private static List<String> findWinnerName(final Cars cars) {
        return cars.getWinner().stream()
                .map(Vehicle::getName)
                .collect(Collectors.toList());
    }

    private static List<VehicleDto> findAllVehicle(final Cars cars) {
        return cars.getCars()
                .stream()
                .map(car -> new VehicleDto(car.getName(), car.getDistance()))
                .collect(Collectors.toList());
    }
}
