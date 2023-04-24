package racingcar.controller;

import java.util.List;
import java.util.Scanner;
import racingcar.domain.RacingGame;
import racingcar.persistence.repository.InMemoryRacingRepository;
import racingcar.service.RacingService;
import racingcar.view.input.InputView;
import racingcar.view.output.ConsoleView;

public final class ConsoleRacingController {

    private final InputView inputView;
    private final ConsoleView consoleView;
    private final RacingService racingService;

    public ConsoleRacingController() {
        this.inputView = new InputView(new Scanner(System.in));
        this.consoleView = new ConsoleView();
        this.racingService = new RacingService(new InMemoryRacingRepository());
    }

    public void run() {
        this.racingService.playRacingGame(inputView.readCarNames(), inputView.readTryCount());
        List<RacingGame> racingGames = racingService.getAllRacingGame();
        consoleView.printAllRacingGames(racingGames);
    }
}
