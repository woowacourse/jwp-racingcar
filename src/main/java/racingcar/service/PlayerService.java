package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dto.PlayRequest;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.Vehicle;
import racingcar.repository.PlayerDao;
import racingcar.util.CarNameValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerDao playerDao;
    private final CarNameValidator carNameValidator;

    @Autowired
    public PlayerService(final PlayerDao playerDao, final CarNameValidator carNameValidator) {
        this.playerDao = playerDao;
        this.carNameValidator = carNameValidator;
    }

    public Cars createCars(final PlayRequest playRequest) {
        String names = playRequest.getNames();

        return createCars(names);
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

    public void saveCars(final Cars cars) {
        cars.getCars().stream()
                .map(Vehicle::getName)
                .filter(playerDao::isNotExist)
                .forEach(playerDao::insert);
    }
}
