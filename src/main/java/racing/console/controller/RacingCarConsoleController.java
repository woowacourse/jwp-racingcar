package racing.console.controller;

import java.util.Arrays;
import java.util.stream.Collectors;
import racing.console.ui.input.InputView;
import racing.console.ui.output.OutputView;
import racing.domain.Car;
import racing.domain.CarName;
import racing.domain.Cars;
import racing.web.controller.dto.request.RacingGameInfoRequest;
import racing.web.controller.dto.request.validator.RacingGameInfoRequestValidator;
import racing.web.controller.dto.request.validator.RequestValidator;

public class RacingCarConsoleController {

    public void playGame() {
        try {
            RacingGameInfoRequest racingGameInfoRequest = requestRacingGameInfo();

            Cars gamingCars = generateCars(racingGameInfoRequest.getNames());
            gamingCars.moveCarsByCount(racingGameInfoRequest.getCount());

            OutputView.printResult(gamingCars);
        } catch (IllegalArgumentException illegalArgumentException) {
            OutputView.printErrorMessage(illegalArgumentException.getMessage());
        }
    }

    private RacingGameInfoRequest requestRacingGameInfo() {
        String carsName = InputView.inputCarsName();
        int inputCount = InputView.inputCount();

        RacingGameInfoRequest racingGameInfoRequest = new RacingGameInfoRequest(carsName, inputCount);
        validateUserInput(racingGameInfoRequest);

        return racingGameInfoRequest;
    }

    private void validateUserInput(RacingGameInfoRequest racingGameInfoRequest) {
        RequestValidator<RacingGameInfoRequest> racingGameInfoRequestValidator = new RacingGameInfoRequestValidator();
        racingGameInfoRequestValidator.validate(racingGameInfoRequest);
    }

    private Cars generateCars(String carNames) {
        return new Cars(Arrays.stream(carNames.split(","))
                .map(CarName::new)
                .map(Car::new)
                .collect(Collectors.toList()));
    }
}
