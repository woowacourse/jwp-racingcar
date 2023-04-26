package racingcar.controller;

import java.util.List;
import racingcar.dto.RacingCarNamesDto;
import racingcar.dto.RacingCarStatusDto;
import racingcar.dto.RacingCarWinnerDto;
import racingcar.dto.TryCountDto;
import racingcar.domain.RacingCarGame;
import racingcar.domain.RandomMoveStrategy;
import racingcar.domain.TryCount;
import racingcar.view.RacingCarView;

public class RacingCarConsoleController {
    private final RacingCarView racingCarView;

    public RacingCarConsoleController(RacingCarView racingCarView) {
        this.racingCarView = racingCarView;
    }

    public void start() {
        RacingCarGame racingCarGame = createRacingCarGame();
        TryCount tryCount = new TryCount(getTryCount());
        playGame(tryCount, racingCarGame);
    }

    private RacingCarGame createRacingCarGame() {
        RacingCarNamesDto racingCarNamesDto = receiveCarNames();
        return RacingCarGame.from(racingCarNamesDto);
    }

    private RacingCarNamesDto receiveCarNames() {
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

    private void playGame(TryCount tryCount, RacingCarGame racingCarGame) {
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy();
        racingCarView.printStartMessage();
        while (tryCount.isAvailable()) {
            racingCarGame.moveCars(randomMoveStrategy);
            tryCount.moveUntilZero();
        }
        printCarStatuses(racingCarGame);
        findWinners(racingCarGame);
    }

    private void findWinners(final RacingCarGame racingCarGame) {
        try {
            RacingCarWinnerDto racingCarWinnerDto = racingCarGame.findWinners();
            racingCarView.printWinners(racingCarWinnerDto);
        } catch (RuntimeException e) {
            racingCarView.printExceptionMessage(e);
        }
    }

    private void printCarStatuses(final RacingCarGame racingCarGame) {
        List<RacingCarStatusDto> carStatuses = racingCarGame.getCarStatuses();
        racingCarView.printRacingProgress(carStatuses);
    }
}
