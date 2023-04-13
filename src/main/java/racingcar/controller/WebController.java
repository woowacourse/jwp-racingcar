package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.UpdatingDAO;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.CarDto;
import racingcar.dto.CarInfoDto;
import racingcar.dto.FinalResultDto;
import racingcar.dto.RequestBodyDTO;
import racingcar.utils.Converter;
import racingcar.utils.RandomNumberGenerator;
import racingcar.vo.CarName;
import racingcar.vo.Trial;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class WebController {
    public static final String DUPLICATING_NAME_EXCEPTION_MESSAGE = "중복된 이름은 사용할 수 없습니다.";
    public static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "입력값은 비어있을 수 없습니다.";

    final private UpdatingDAO updatingDAO;

    public WebController(UpdatingDAO updatingDAO){
        this.updatingDAO=updatingDAO;
    }

    @PostMapping("/plays")
    public FinalResultDto run(@RequestBody RequestBodyDTO dto) {
        Cars cars = initializeCars(dto.getNames());
        Trial trial = getTrial(dto.getCount());
        Cars updatedCars = playGame(cars, trial);
        final int racingId=updatingDAO.insert(trial);
        List<CarDto> carDtos = cars.getCarDtos();
        List<String> winnerNames = cars.getWinnerNames();

        for(CarDto car : carDtos){
            String name = car.getName();
            updatingDAO.insert(new CarInfoDto(racingId, name, car.getPosition(), winnerNames.contains(name)));
        }
        return new FinalResultDto(updatedCars.getWinnerNames(), updatedCars.getCarDtos());
    }

    public Trial getTrial(String input) {
        return Trial.of(Converter.convertStringToLong(input));
    }

    private Cars playGame(Cars cars, Trial trial) {
        for (int count = 0; count < trial.getValue(); count++) {
            cars.move();
        }
        return cars;
    }

    private Cars initializeCars(String names) {
        List<CarName> carNames = getCarNames(names);
        validateDuplication(carNames);
        return saveCars(carNames);
    }

    private List<CarName> getCarNames(String names) {
        List<CarName> carNames = CarName.of(
                Arrays.asList(names.split(","))
        );

        validateCarNames(carNames);
        return carNames;
    }

    private void validateCarNames(List<CarName> carNames) {
        hasNoNames(carNames);

        for (CarName carName : carNames) {
            isNameBlank(carName);
        }
    }

    private static void hasNoNames(List<CarName> carNames) {
        if (carNames.size() == 0) {
            throw new IllegalArgumentException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private static void isNameBlank(CarName carName) {
        if (carName.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private Cars saveCars(List<CarName> carNames) {
        List<Car> cars = carNames.stream().map(Car::of)
                .collect(Collectors.toList());
        return new Cars(cars, RandomNumberGenerator.makeInstance());
    }

    private void validateDuplication(List<CarName> names) {
        Set<CarName> namesWithoutDuplication = new HashSet<>(names);
        if (names.size() != namesWithoutDuplication.size()) {
            throw new IllegalArgumentException(DUPLICATING_NAME_EXCEPTION_MESSAGE);
        }
    }
}
