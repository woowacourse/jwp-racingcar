package racingcar.controller;

import racingcar.domain.Game;
import racingcar.service.GameService;
import racingcar.service.dto.SingleGameResult;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class ConsoleGameController {

    private static final String EXCEPTION_PREFIX = "[ERROR]";

    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    private final GameService gameService;

    public ConsoleGameController(final GameService gameService) {
        this.gameService = gameService;
    }

    public void play() {
        final Game game = createGame();
        final SingleGameResult result = gameService.play(game);
        showResult(result);
    }

    private Game createGame() {
        return handleExceptionByRepeating(() -> {
            List<String> carNames = INPUT_VIEW.inputCarNames();
            int trialCount = INPUT_VIEW.inputTrialCount();
            return gameService.createGameWith(carNames, trialCount);
        });
    }

    private void showResult(final SingleGameResult singleGameResult) {
        OUTPUT_VIEW.noticeResult();
        OUTPUT_VIEW.printStatusOf(singleGameResult.getRacingCars());
        OUTPUT_VIEW.printWinners(singleGameResult.getWinners());
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
