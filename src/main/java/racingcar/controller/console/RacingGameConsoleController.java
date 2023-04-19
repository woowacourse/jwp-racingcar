package racingcar.controller.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import racingcar.controller.dto.RacingGameResponse;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.Map;

@Component
public class RacingGameConsoleController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;
    private final Map<ConsoleCommand, Active> activities;

    public RacingGameConsoleController(final RacingGameService racingGameService) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.racingGameService = racingGameService;
        activities = setUpConsoleCommands();
    }

    private Map<ConsoleCommand, Active> setUpConsoleCommands() {
        return Map.of(
                ConsoleCommand.RACE, this::race,
                ConsoleCommand.HISTORY, this::getHistories
        );
    }

    private void race() {
        final List<String> nameValues = inputView.readCarNames();
        final int trial = inputView.readMovingTrial();
        final RacingGameResponse response = racingGameService.race(nameValues, trial);
        outputView.printRacingResult(response);
    }

    private void getHistories() {
        final List<RacingGameResponse> response = racingGameService.findAllRacingGameHistories();
        outputView.printRacingHistories(response);
    }

    public boolean run() {
        try {
            final ConsoleCommand consoleCommand = inputView.readCommand();
            activities.get(consoleCommand).execute();
        } catch (IllegalArgumentException | IllegalStateException e) {
            log.error("{}", e.getMessage());
            return false;
        }
        return true;
    }
}
