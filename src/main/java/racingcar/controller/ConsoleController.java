package racingcar.controller;

import java.util.List;

import racingcar.model.Car;
import racingcar.service.ConsoleService;
import racingcar.ui.OutputView;

public class ConsoleController {

    private final ConsoleService racingcarService;
    private final OutputView outputView;

    public ConsoleController(OutputView outputView, ConsoleService racingcarService) {
        this.racingcarService = racingcarService;
        this.outputView = outputView;
    }

    public void run(int tryCount) {
        playGame(tryCount);
        findWinners();
    }

    private void playGame(int tryCount) {
        outputView.playRound();
        for (int i = 0; i < tryCount; i++) {
            List<Car> movedCars = racingcarService.move();
            outputView.result(movedCars);
        }
    }

    private void findWinners() {
        List<Car> winners = racingcarService.findWinners();
        outputView.winner(winners);
    }
}
