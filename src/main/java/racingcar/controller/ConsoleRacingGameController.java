package racingcar.controller;

import racingcar.dto.GameResultResponse;
import racingcar.dto.RacingGameRequest;
import racingcar.persistence.repository.InMemoryGameRepository;
import racingcar.service.RacingGameService;
import racingcar.view.input.InputView;
import racingcar.view.output.ConsoleView;

import java.util.List;

public class ConsoleRacingGameController {

    private final InputView inputView;
    private final ConsoleView consoleView;

    public ConsoleRacingGameController(InputView inputView, ConsoleView consoleView) {
        this.inputView = inputView;
        this.consoleView = consoleView;
    }

    public void start() {
        RacingGameRequest racingGameRequest = new RacingGameRequest(inputView.readCarName(), inputView.readGameTry());
        RacingGameService racingGameService = new RacingGameService(new InMemoryGameRepository());
        racingGameService.playRacingGame(racingGameRequest);
        List<GameResultResponse> gameRecordResponse = racingGameService.makeGameRecords();
        consoleView.renderRacingGameResult(gameRecordResponse);
    }
}
