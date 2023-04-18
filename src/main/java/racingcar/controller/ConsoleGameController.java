package racingcar.controller;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.domain.MoveChance;
import racingcar.domain.RandomMoveChance;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleGameController {

    private static final String EXCEPTION_PREFIX = "[ERROR]";

    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    private final Game game;
    private final MoveChance moveChance;

    public ConsoleGameController() {
        game = createGame();
        moveChance = RandomMoveChance.getInstance();
    }

    private Game createGame() {
        return handleExceptionByRepeating(() -> {
            List<String> carNames = INPUT_VIEW.inputCarNames();
            int trialCount = INPUT_VIEW.inputTrialCount();
            return new Game(makeCarsWith(carNames), trialCount);
        });
    }

    public void play() {
        OUTPUT_VIEW.noticeResult();
        playMultipleTimes();
        showResult();
    }

    private List<Car> makeCarsWith(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void playMultipleTimes() {
        while (game.isNotDone()) {
            game.playOnceWith(moveChance);
            OUTPUT_VIEW.printStatusOf(game.getCars());
        }
    }

    private void showResult() {
        OUTPUT_VIEW.printStatusOf(game.getCars());
        OUTPUT_VIEW.printWinners(game.findWinners());
    }

    private <T> T handleExceptionByRepeating(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception exception) {
            System.out.println(EXCEPTION_PREFIX + exception.getMessage());
            return handleExceptionByRepeating(supplier);
        }
    }
}
