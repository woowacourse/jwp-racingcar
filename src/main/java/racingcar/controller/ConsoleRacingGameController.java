package racingcar.controller;

import racingcar.controller.util.NameParser;
import racingcar.dao.RacingCarGameConsoleDao;
import racingcar.dao.RacingCarPlayerConsoleDao;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingCarResponse;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingGameController {

    private final RacingGameService racingGameService;

    public ConsoleRacingGameController() {
        this.racingGameService = new RacingGameService(
                new RacingCarGameConsoleDao(),
                new RacingCarPlayerConsoleDao());
    }

    public void play() {
        final RacingCarRequest racingCarRequest = getRacingCarRequest();

        final RacingCarResponse racingCarResponse = racingGameService.play(
                NameParser.getSlicedName(racingCarRequest.getNames()),
                racingCarRequest.getTryCount());

        OutputView.printGameResult(racingCarResponse);
    }

    private RacingCarRequest getRacingCarRequest() {
        final String names = getNames();
        final int tryCount = getTryCount();

        return new RacingCarRequest(names, tryCount);
    }

    private String getNames() {
        try {
            return InputView.requestCarName();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getNames();
        }
    }

    private int getTryCount() {
        try {
            return InputView.requestTryCount();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getTryCount();
        }
    }
}
