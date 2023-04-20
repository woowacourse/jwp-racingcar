package racingcar.controller;

import racingcar.dao.CarsDao;
import racingcar.dao.GamesDao;
import racingcar.dao.WinnersDao;
import racingcar.domain.Game;
import racingcar.service.GameService;
import racingcar.service.dto.GameResult;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class ConsoleGameController {

    private static final String EXCEPTION_PREFIX = "[ERROR]";

    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    private final GameService gameService;


    public ConsoleGameController(
            final GamesDao gamesDao,
            final CarsDao carsDao,
            final WinnersDao winnersDao
    ) {
        this.gameService = new GameService(gamesDao, carsDao, winnersDao);
    }

    public void play() {
        final Game game = createGame();
        final GameResult result = gameService.play(game);
        showResult(result);
    }

    private Game createGame() {
        return handleExceptionByRepeating(() -> {
            List<String> carNames = INPUT_VIEW.inputCarNames();
            int trialCount = INPUT_VIEW.inputTrialCount();
            return gameService.createGameWith(carNames, trialCount);
        });
    }

    private void showResult(final GameResult gameResult) {
        OUTPUT_VIEW.noticeResult();
        OUTPUT_VIEW.printStatusOf(gameResult.getRacingCars());
        OUTPUT_VIEW.printWinners(gameResult.getWinners());
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
