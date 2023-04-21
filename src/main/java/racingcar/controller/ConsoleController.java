package racingcar.controller;

import racingcar.dto.RacingCarResponseDto;
import racingcar.model.Cars;
import racingcar.model.Trial;
import racingcar.service.RacingCarService;
import racingcar.ui.ConsoleView;

public class ConsoleController {
    private final ConsoleView consoleView;
    private final RacingCarService racingcarService;

    public ConsoleController(ConsoleView consoleView, RacingCarService racingcarService) {
        this.consoleView = consoleView;
        this.racingcarService = racingcarService;
    }

    public void run() {
        try {
            Cars cars = Cars.from(consoleView.carNames());
            Trial trial = new Trial(consoleView.tryCount());
            RacingCarResponseDto racingCarResponseDto = racingcarService.play(cars, trial);
            consoleView.printResult(racingCarResponseDto);
        } catch (IllegalArgumentException e) {
            consoleView.error(e.getMessage());
        }
    }
}
