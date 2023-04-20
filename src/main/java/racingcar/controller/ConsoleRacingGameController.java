package racingcar.controller;

import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.CarData;
import racingcar.dto.GameResultResponse;
import racingcar.dto.RacingGameRequest;
import racingcar.persistence.repository.InMemoryGameRepository;
import racingcar.service.RacingGameService;
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
                .map(this::toGameResultResponse)
                .collect(Collectors.toList());
        consoleView.renderRacingGameResult(gameRecordResponse);
    }

    private GameResultResponse toGameResultResponse(final RacingGame racingGame) {
        return new GameResultResponse(
                toWinnerResponse(racingGame),
                toCarDataResponse(racingGame)
        );
    }

    private List<CarData> toCarDataResponse(final RacingGame racingGame) {
        return racingGame.getCars()
                .stream()
                .map(car -> new CarData(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    private String toWinnerResponse(final RacingGame racingGame) {
        return racingGame.getWinners()
                .stream()
                .map(Car::getCarName)
                .reduce((o1, o2) -> o1 + "," + o2)
                .orElseThrow();
    }
}
