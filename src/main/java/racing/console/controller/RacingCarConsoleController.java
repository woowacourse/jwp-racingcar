package racing.console.controller;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import racing.console.ui.input.InputView;
import racing.console.ui.output.OutputView;
import racing.domain.Car;
import racing.domain.CarName;
import racing.domain.Cars;
import racing.domain.RacingCarGame;
import racing.domain.TrialCount;
import racing.domain.utils.RacingCarNumberGenerator;
import racing.domain.utils.RandomRacingCarNumberGenerator;

public class RacingCarConsoleController {

    private final InputView inputView;
    private final OutputView outputView;

    public RacingCarConsoleController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void playGame() {
        RacingCarGame racingCarGame = requestRacingGame();

        RacingCarNumberGenerator racingCarNumberGenerator
                = new RandomRacingCarNumberGenerator();
        racingCarGame.playRounds(racingCarNumberGenerator);

        outputView.printResult(racingCarGame.getCars());
        outputView.printWinners(racingCarGame.winnerCars());
    }

    private RacingCarGame requestRacingGame() {
        Cars cars = retryOnInvalidInput(this::requestCars);
        TrialCount trialCount = retryOnInvalidInput(this::requestTrialCount);

        return new RacingCarGame(cars, trialCount);
    }

    private Cars requestCars() {
        List<String> carNames = inputView.readCarNames();

        return new Cars(carNames.stream()
                .map(CarName::new)
                .map(Car::new)
                .collect(Collectors.toList()));
    }

    private TrialCount requestTrialCount() {
        String trialCount = inputView.readTrialCount();

        return TrialCount.valueOf(trialCount);
    }

    private <T> T retryOnInvalidInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return retryOnInvalidInput(request);
        }
    }
}
