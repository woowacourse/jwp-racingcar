package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameInfo;
import racingcar.dto.WinnerCarDto;
import racingcar.exception.DuplicateCarNameException;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.utils.RacingNumberGenerator;
import racingcar.utils.RacingRandomNumberGenerator;
import racingcar.utils.StringUtils;
import racingcar.wrapper.Round;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarController {

    private Cars cars;

    @PostMapping("/plays")
    public List<WinnerCarDto> run(@RequestBody GameInfo gameInfo) {
        final RacingNumberGenerator generator = new RacingRandomNumberGenerator();
        cars = generateCars(gameInfo.getNames());
        Round round = new Round(gameInfo.getCount());

        for (int count = 0; count < round.getRound(); count++) {
            cars.race(generator);
        }

        return cars.getWinner();
    }


    public Cars generateCars(String inputCarsName) {
        String[] carsName = StringUtils.splitBySeparator(inputCarsName);
        checkDuplication(carsName);
        return new Cars(mapToCars(carsName));
    }

    private List<Car> mapToCars(String[] carsName) {
        return Arrays.stream(carsName)
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void checkDuplication(String[] carsName) {
        if (getDistinctCarsCount(carsName) != carsName.length) {
            throw new DuplicateCarNameException();
        }
    }

    private long getDistinctCarsCount(String[] carsName) {
        return Arrays.stream(carsName)
                .distinct()
                .count();
    }
}
