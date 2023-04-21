package racingcar.controller;

import racingcar.model.Cars;
import racingcar.model.Trial;
import racingcar.service.RacingResponse;
import racingcar.service.RacingcarService;
import racingcar.ui.ConsoleView;

public class ConsoleController {
    private final ConsoleView consoleView;
    private final RacingcarService racingcarService;

    public ConsoleController(ConsoleView consoleView, RacingcarService racingcarService) {
        this.consoleView = consoleView;
        this.racingcarService = racingcarService;
    }

    public void run() {
        try {
            Cars cars = Cars.from(consoleView.carNames());
            Trial trial = new Trial(consoleView.tryCount());
            RacingResponse racingResponse = racingcarService.play(cars, trial);
            consoleView.printResult(racingResponse);
        } catch (IllegalArgumentException e) {
            consoleView.error(e.getMessage());
        }
    }
}
