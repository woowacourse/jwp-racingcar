package racing;

import java.util.Arrays;
import java.util.stream.Collectors;
import racing.controller.dto.request.RacingGameInfoRequest;
import racing.controller.dto.request.validator.RacingGameInfoRequestValidator;
import racing.controller.dto.request.validator.RequestValidator;
import racing.domain.Car;
import racing.domain.CarName;
import racing.domain.Cars;
import racing.ui.input.InputView;
import racing.ui.output.OutputView;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        String carsName = InputView.inputCarsName();
        int inputCount = InputView.inputCount();

        RacingGameInfoRequest racingGameInfoRequest = new RacingGameInfoRequest(carsName, inputCount);
        RequestValidator<RacingGameInfoRequest> racingGameInfoRequestValidator = new RacingGameInfoRequestValidator();
        racingGameInfoRequestValidator.validate(racingGameInfoRequest);

        Cars gamingCars = new Cars(Arrays.stream(carsName.split(","))
                .map(CarName::new)
                .map(Car::new)
                .collect(Collectors.toList()));

        gamingCars.moveCarsByCount(inputCount);

        OutputView.printResult(gamingCars);
    }
}
