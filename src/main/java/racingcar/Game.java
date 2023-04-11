package racingcar;

import racingcar.controller.RaceController;
import racingcar.domain.Car;
import java.util.List;
import racingcar.util.NumberGenerator;
import racingcar.view.input.InputView;
import racingcar.view.output.OutputView;

public class Game {

    private final RaceController raceController;

    public Game(RaceController raceController) {
        this.raceController = raceController;
    }

    public void ready(InputView inputView) {
        raceController.getParticipants(inputView);
        raceController.getCount(inputView);
    }

    public void playGame(NumberGenerator numberGenerator, OutputView outputView) {
        while (!raceController.isFinished()) {
            raceOneRound(numberGenerator, outputView);
        }
        raceController.printRoundResult(outputView);
    }

    private void raceOneRound(NumberGenerator numberGenerator, OutputView outputView) {
        List<Car> cars = raceController.showStatus();
        if (!raceController.isFinished()) {
            cars.forEach((car) -> raceController.driveOrNot(car, numberGenerator));
            raceController.roundFinished();
        }
        raceController.printRoundResult(outputView);
    }

    public void showResult(OutputView outputView) {
        outputView.printResultMessage();
        outputView.printWinners(raceController.getWinners());
    }
}
