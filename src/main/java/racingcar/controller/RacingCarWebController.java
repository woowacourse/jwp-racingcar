package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.CarStatusResponse;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.model.Car;
import racingcar.model.RacingCars;
import racingcar.util.NumberGenerator;
import racingcar.view.InputViewValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingCarWebController {

    private final InputViewValidator inputViewValidator = new InputViewValidator();
    private final NumberGenerator numberGenerator;

    private static final String CAR_NAMES_DELIMITER = ",";
    private static final int START_POSITION = 0;

    public RacingCarWebController(final NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @PostMapping("/plays")
    public RaceResultResponse showRaceResults(@RequestBody GameInfoRequest gameInfoRequest) {

        final String names = gameInfoRequest.getNames();

        inputViewValidator.validateCarNames(names);
        String[] splitCarNames = getSplitCarNames(names);
        inputViewValidator.validateSplitCarNames(splitCarNames);

        RacingCars racingCars = generateRacingCarsStep(splitCarNames);

        final int tryCount = gameInfoRequest.getCount();

        racingCars.moveAllCars(tryCount, numberGenerator);

        final List<CarStatusResponse> carStatusResponses =
                racingCars.getCars()
                          .stream()
                          .map(car -> new CarStatusResponse(car.getName(),
                                                            car.getPosition()))
                          .collect(Collectors.toList());

        return new RaceResultResponse(findWinners(racingCars), carStatusResponses);
    }

    private RacingCars generateRacingCarsStep(String[] carNames) {
        List<Car> cars = generateCars(carNames);
        return new RacingCars(cars);
    }

    private String[] getSplitCarNames(String carNames) {
        return carNames.split(CAR_NAMES_DELIMITER, -1);
    }

    private List<Car> generateCars(String[] carNames) {
        return Arrays.stream(carNames)
                     .map(carName -> new Car(carName, START_POSITION))
                     .collect(Collectors.toUnmodifiableList());
    }

    private List<String> findWinners(RacingCars racingCars) {
        return racingCars.getWinners()
                         .stream()
                         .map(Car::getName)
                         .collect(Collectors.toList());
    }
}
