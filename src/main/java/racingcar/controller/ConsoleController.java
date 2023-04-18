package racingcar.controller;

import java.util.List;
import racingcar.dto.NamesDto;
import racingcar.dto.ResultDto;
import racingcar.dto.TryCountDto;
import racingcar.dto.WinnerDto;
import racingcar.domain.Game;
import racingcar.domain.RandomMoveStrategy;
import racingcar.domain.TryCount;
import racingcar.view.RacingCarView;

public class ConsoleController {
    private final Game game;
    private final RacingCarView racingCarView;

    public ConsoleController(Game game, RacingCarView racingCarView) {
        this.game = game;
        this.racingCarView = racingCarView;
    }

    public void start() {
        createCar();
        TryCount tryCount = new TryCount(getTryCount());
        playGame(tryCount);
    }

    private void createCar() {
        NamesDto namesDto = receiveCarNames();
        game.createCars(namesDto);
    }

    private NamesDto receiveCarNames() {
        try {
            return racingCarView.receiveCarNames();
        } catch (RuntimeException e) {
            racingCarView.printExceptionMessage(e);
            return receiveCarNames();
        }
    }

    private int getTryCount() {
        TryCountDto tryCountDto = receiveTryCount();
        return tryCountDto.getTryCount();
    }

    private TryCountDto receiveTryCount() {
        try {
            return racingCarView.receiveTryCount();
        } catch (RuntimeException e) {
            racingCarView.printExceptionMessage(e);
            return receiveTryCount();
        }
    }

    private void playGame(TryCount tryCount) {
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy();
        racingCarView.printStartMessage();
        while (tryCount.isAvailable()) {
            game.moveCars(randomMoveStrategy);
            tryCount.moveUntilZero();
        }
        printCarStatuses();
        findWinners();
    }

    private void findWinners() {
        try {
            WinnerDto winnerDto = game.findWinners();
            racingCarView.printWinners(winnerDto);
        } catch (RuntimeException e) {
            racingCarView.printExceptionMessage(e);
        }
    }

    private void printCarStatuses() {
        List<ResultDto> carStatuses = game.getCarStatuses();
        racingCarView.printRacingProgress(carStatuses);
    }
}
