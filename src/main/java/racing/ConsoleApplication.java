package racing;

import racing.controller.ConsoleRacingGameController;
import racing.domain.CarFactory;
import racing.domain.Cars;
import racing.ui.input.InputView;
import racing.ui.output.OutputView;

import java.util.Arrays;
import java.util.List;

public class ConsoleApplication {

    public static void main(String[] args) {
        ConsoleRacingGameController consoleRacingGameController = new ConsoleRacingGameController(getCars());
        int count = Integer.parseInt(InputView.inputCount());
        consoleRacingGameController.start(count);
    }

    private static Cars getCars() {
        List<String> carNames = Arrays.asList(InputView.inputCarsName().split(OutputView.COMMA));
        return CarFactory.carFactory(carNames);
    }

}
