package racingcar.controller;

import java.util.List;
import racingcar.dto.RacingCarNamesDto;
import racingcar.dto.RacingCarStatusDto;
import racingcar.dto.RacingCarWinnerDto;
import racingcar.dto.TryCountDto;
import racingcar.service.RacingCarGame;
import racingcar.service.RandomMoveStrategy;
import racingcar.service.TryCount;
import racingcar.view.RacingCarView;

public class RacingCarController {
    private final RacingCarGame racingCarGame;
    private final RacingCarView racingCarView;

    public RacingCarController(RacingCarGame racingCarGame, RacingCarView racingCarView) {
        this.racingCarGame = racingCarGame;
        this.racingCarView = racingCarView;
    }

    public void start() {
        createCar();
        TryCount tryCount = new TryCount(getTryCount());
        playGame(tryCount);
    }

    private void createCar() {
        RacingCarNamesDto racingCarNamesDto = receiveCarNames();
        racingCarGame.createCars(racingCarNamesDto);
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

    private void playGame(TryCount tryCount) {
        RandomMoveStrategy randomMoveStrategy = new RandomMoveStrategy();
        racingCarView.printStartMessage();
        while (tryCount.isAvailable()) {
            racingCarGame.moveCars(randomMoveStrategy);
            tryCount.moveUntilZero();
        }
        printCarStatuses();
        findWinners();
    }

    private void findWinners() {
        try {
            RacingCarWinnerDto racingCarWinnerDto = racingCarGame.findWinners();
            racingCarView.printWinners(racingCarWinnerDto);
        } catch (RuntimeException e) {
            racingCarView.printExceptionMessage(e);
        }
    }

    private void printCarStatuses() {
        List<RacingCarStatusDto> carStatuses = racingCarGame.getCarStatuses();
        racingCarView.printRacingProgress(carStatuses);
    }
}
