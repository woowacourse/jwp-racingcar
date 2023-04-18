package racingcar.controller;

import java.util.List;
import racingcar.dto.NamesDto;
import racingcar.dto.ResultDto;
import racingcar.dto.TryCountDto;
import racingcar.dto.WinnerDto;
import racingcar.service.RacingCarService;
import racingcar.service.RandomMoveStrategy;
import racingcar.service.TryCount;
import racingcar.view.RacingCarView;

public class ConsoleController {
    private final RacingCarService racingCarService;
    private final RacingCarView racingCarView;

    public ConsoleController(RacingCarService racingCarService, RacingCarView racingCarView) {
        this.racingCarService = racingCarService;
        this.racingCarView = racingCarView;
    }

    public void start() {
        createCar();
        TryCount tryCount = new TryCount(getTryCount());
        playGame(tryCount);
    }

    private void createCar() {
        NamesDto namesDto = receiveCarNames();
        racingCarService.createCars(namesDto);
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
            racingCarService.moveCars(randomMoveStrategy);
            tryCount.moveUntilZero();
        }
        printCarStatuses();
        findWinners();
    }

    private void findWinners() {
        try {
            WinnerDto winnerDto = racingCarService.findWinners();
            racingCarView.printWinners(winnerDto);
        } catch (RuntimeException e) {
            racingCarView.printExceptionMessage(e);
        }
    }

    private void printCarStatuses() {
        List<ResultDto> carStatuses = racingCarService.getCarStatuses();
        racingCarView.printRacingProgress(carStatuses);
    }
}
