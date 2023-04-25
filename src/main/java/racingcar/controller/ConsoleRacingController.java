package racingcar.controller;

import static racingcar.controller.ConsoleCommand.FIND;
import static racingcar.controller.ConsoleCommand.QUIT;
import static racingcar.controller.ConsoleCommand.SAVE;

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
        ConsoleCommand consoleCommand = this.inputView.readCommand();
        while (!consoleCommand.equals(QUIT)) {
            if (consoleCommand.equals(SAVE)) {
                RacingGame racingGame = this.racingService.playRacingGame(
                        this.inputView.readCarNames(),
                        this.inputView.readTryCount()
                );
                this.consoleView.printRacingGame(racingGame);
            }
            if (consoleCommand.equals(FIND)) {
                List<RacingGame> racingGames = this.racingService.getAllRacingGame();
                this.consoleView.printAllRacingGames(racingGames);
            }
            consoleCommand = this.inputView.readCommand();
        }
    }
}
