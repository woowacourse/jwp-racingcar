package racingcar.controller;

import java.util.List;
import racingcar.GameDto;
import racingcar.GameResultDto;
import racingcar.GameService;
import racingcar.dto.NamesDto;
import racingcar.dto.ResultDto;
import racingcar.dto.TryCountDto;
import racingcar.view.RacingCarView;

public class ConsoleController {
    private final RacingCarView racingCarView;
    private final GameService gameService;

    public ConsoleController(RacingCarView racingCarView, GameService gameService) {
        this.racingCarView = racingCarView;
        this.gameService = gameService;
    }

    public void run() {
        final List<String> names = receiveCarNames().getNames();
        final int tryCount = getTryCount();
        final GameDto gameDto = new GameDto(names, tryCount);
        final GameResultDto gameResult = gameService.play(gameDto);
        final List<ResultDto> racingCars = gameResult.getRacingCars();
        printCarStatuses(racingCars);
        final String winners = gameResult.getWinners();
        printWinners(winners);
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

    private void printCarStatuses(final List<ResultDto> racingCars) {
        racingCarView.printRacingProgress(racingCars);
    }

    private void printWinners(final String winners) {
        racingCarView.printWinners(winners);
    }
}
