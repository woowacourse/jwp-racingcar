package racingcar.controller;

import racingcar.domain.Car.TryCount;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    public void run() {
        RacingGameResponseDto result = play();
        OutputView.printResultMessage();
        showFinalResult(result);
    }

    private RacingGameResponseDto play() {
        try {
            List<String> names = InputView.inputCarNames();
            TryCount tries = new TryCount(InputView.inputTries());
            RacingGameResponseDto result = racingGameService.runWithoutDb(names, tries.getTries());
            return result;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return play();
        }
    }

    private void showFinalResult(RacingGameResponseDto result) {
        showRaceResult(result.getRacingCars());
        showWinners(result.getWinners());
    }

    private void showRaceResult(List<CarDto> racingCars) {
        List<CarDto> cars = racingCars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
        OutputView.printRaceResult(cars);
    }

    private void showWinners(String winners) {
        OutputView.printFinalResult(winners);
    }
}
