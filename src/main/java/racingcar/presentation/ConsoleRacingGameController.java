package racingcar.presentation;

import racingcar.business.RacingGameService;
import racingcar.presentation.dto.GameResultResponse;
import racingcar.presentation.dto.RacingGameRequest;
import racingcar.domain.RacingGame;
import racingcar.persistence.repository.InMemoryGameRepository;
import racingcar.view.input.InputView;
import racingcar.view.output.ConsoleView;

import java.util.List;
import java.util.stream.Collectors;

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
        List<RacingGame> allGames = racingGameService.makeGameRecords();

        List<GameResultResponse> gameRecordResponse = allGames.stream()
                .map(ClientResponseConverter::toGameResultResponse)
                .collect(Collectors.toList());
        consoleView.renderRacingGameResult(gameRecordResponse);
    }
}
