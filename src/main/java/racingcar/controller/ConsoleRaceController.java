package racingcar.controller;

import racingcar.common.ExecuteContext;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.service.RaceService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRaceController {

    private final RaceService raceService;
    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleRaceController(final RaceService raceService, final InputView inputView,
        final OutputView outputView) {
        this.raceService = raceService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        ExecuteContext.repeatableExecute(() -> {
            final RaceRequest raceRequest = inputView.getRaceRequest();
            final RaceResponse raceResponse = raceService.play(raceRequest);
            outputView.printRaceResponse(raceResponse);
            return raceResponse;
        });
    }
}
