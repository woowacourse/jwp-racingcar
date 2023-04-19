package racingcar.controller;

import racingcar.domain.Car;
import racingcar.service.ConsoleService;
import racingcar.domain.RandomMoveChance;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

import static racingcar.option.Option.MIN_TRIAL_COUNT;

public class ConsoleController {
    private final ConsoleService consoleService;
    private final int trialCount;

    public ConsoleController() {
        List<String> carNames = List.of(InputView.inputCarNames());
        consoleService = new ConsoleService(makeCarsWith(carNames), new RandomMoveChance());
        trialCount = InputView.inputTrialCount();
        validateNotNegativeInteger(trialCount);
    }

    public void play() {
        OutputView.noticeResult();
        playMultipleTimes();
    }

    public void showResult() {
        OutputView.printCars(consoleService.getCars());
        OutputView.printWinners(consoleService.findWinners());
    }

    private List<Car> makeCarsWith(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void validateNotNegativeInteger(int trialCount) {
        if (trialCount < MIN_TRIAL_COUNT) {
            throw new IllegalArgumentException("[ERROR] 시도횟수는 음수이면 안됩니다.");
        }
    }

    private void playMultipleTimes() {
        for (int i = 0; i < trialCount; i++) {
            consoleService.playOnce();
        }
    }
}
