package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.Lap;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RandomNumberGenerator;
import racingcar.service.GameService;
import racingcar.service.dto.GameResponseDto;
import racingcar.service.dto.PlayerResultResponseDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleRacingCarController {
    private static final int MINIMUM_RANDOM_NUMBER = 0;
    private static final int MAXIMUM_RANDOM_NUMBER = 9;

    private final NumberGenerator numberGenerator;

    public ConsoleRacingCarController() {
        this.numberGenerator = new RandomNumberGenerator(MINIMUM_RANDOM_NUMBER, MAXIMUM_RANDOM_NUMBER);
    }

    public void run() {
        final Cars cars = initCars();
        final Lap lap = initTryCount();
        OutputView.printResultMessage();
        final GameResponseDto result = GameService.race(cars, lap, numberGenerator);
        prizeWinner(result.getWinners());
        showFinalStatus(cars);
    }

    private Cars initCars() {
        try {
            final String input = InputView.inputCarNames();
            final List<String> carNames = splitCarNames(input);
            return new Cars(carNames);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return initCars();
        }
    }

    private List<String> splitCarNames(final String input) {
        return List.of(input.split(","));
    }

    private Lap initTryCount() {
        try {
            final int tries = InputView.inputTries();
            return new Lap(tries);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return initTryCount();
        }
    }

    private void prizeWinner(final String winners) {
        OutputView.printFinalResult(winners);
    }

    private void showFinalStatus(final Cars cars) {
        final List<PlayerResultResponseDto> playerResultResponseDtos = cars.getLatestResult().stream()
                .map(PlayerResultResponseDto::createByDomain)
                .collect(Collectors.toUnmodifiableList());
        OutputView.printCarStatus(playerResultResponseDtos);
    }
}
