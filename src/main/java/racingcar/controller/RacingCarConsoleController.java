package racingcar.controller;

import static racingcar.view.ConsoleCommand.PLAY;
import static racingcar.view.ConsoleCommand.RECORDS;

import java.util.List;
import racingcar.dto.PlayResponseDto;
import racingcar.repository.RacingCarRepository;
import racingcar.repository.dao.InMemoryCarsDao;
import racingcar.repository.dao.InMemoryPlayRecordsDao;
import racingcar.service.RacingCarService;
import racingcar.view.ConsoleCommand;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final RacingCarService racingCarService = new RacingCarService(
            new RacingCarRepository(new InMemoryPlayRecordsDao(), new InMemoryCarsDao()));

    public void run() {
        while (true) {
            ConsoleCommand command = inputView.askCommand();
            runService(command);
        }
    }

    private void runService(ConsoleCommand command) {
        if (command == PLAY) {
            play();
        }
        if (command == RECORDS) {
            records();
        }
    }

    private void play() {
        final List<String> carNames = inputView.askCarNames();
        final int racingCount = inputView.askRacingCount();

        final PlayResponseDto playResult = racingCarService.playGame(racingCount, carNames);

        outputView.printPlayResult(playResult);
    }

    private void records() {
        outputView.printPlayRecords(racingCarService.findAllPlayRecords());
    }
}
