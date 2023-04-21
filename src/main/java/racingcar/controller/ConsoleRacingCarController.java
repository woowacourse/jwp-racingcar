package racingcar.controller;

import static racingcar.controller.WebRacingCarController.splitNames;

import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.service.RacingCarService;
import racingcar.view.ConsoleInputView;
import racingcar.view.ConsoleOutputView;

public class ConsoleRacingCarController {

    private final ConsoleInputView inputView = new ConsoleInputView();
    private final ConsoleOutputView outputView = new ConsoleOutputView();
    private final RacingCarService racingCarService;

    public ConsoleRacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    public void run() {
        final Cars cars = makeCars();
        final TryCount tryCount = makeTryCount();
        outputView.printResultMessage();
        racingCarService.playRound(cars, tryCount);
        outputView.printWinners(cars.getWinner());
        outputView.printRoundResult(cars.getCars());
    }

    private Cars makeCars() {
        try {
            outputView.printNameInput();
            final String names = inputView.readCarNames();
            return Cars.from(splitNames(names));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeCars();
        }
    }

    private TryCount makeTryCount() {
        try {
            outputView.printCountInput();
            return new TryCount(inputView.readTryCount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeTryCount();
        }
    }
}
