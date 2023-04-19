package racing.console.controller;

import java.util.Arrays;
import java.util.stream.Collectors;
import racing.console.ui.input.InputView;
import racing.console.ui.output.OutputView;
import racing.domain.Car;
import racing.domain.CarName;
import racing.domain.Cars;

public class RacingCarConsoleController {

    public void playGame() {
        try {
            String carsName = InputView.inputCarsName();
            int inputCount = InputView.inputCount();

            Cars gamingCars = generateCars(carsName);
            gamingCars.moveCarsByCount(inputCount);

            OutputView.printResult(gamingCars);
        } catch (IllegalArgumentException illegalArgumentException) {
            OutputView.printErrorMessage(illegalArgumentException.getMessage());
        }
    }

    private Cars generateCars(String carNames) {
        return new Cars(Arrays.stream(carNames.split(","))
                .map(CarName::new)
                .map(Car::new)
                .collect(Collectors.toList()));
    }
}
