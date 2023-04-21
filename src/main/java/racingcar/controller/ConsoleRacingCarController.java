package racingcar.controller;

import static racingcar.controller.WebRacingCarController.splitNames;

import java.util.List;
import racingcar.service.dto.RacingCarGameResultDto;
import racingcar.service.RacingCarService;
import racingcar.view.ConsoleInputView;
import racingcar.view.ConsoleOutputView;

public class ConsoleRacingCarController {

    private final ConsoleInputView inputView = new ConsoleInputView();
    private final ConsoleOutputView outputView = new ConsoleOutputView();
    private final RacingCarService racingCarService;

    public ConsoleRacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    public void run() {
        final List<String> playerNames = readPlayerNames();
        final int tryCount = readTryCount();
        outputView.printResultMessage();
        final RacingCarGameResultDto racingCarGameResultDto = racingCarService.playRound(playerNames, tryCount);
        outputView.printWinners(racingCarGameResultDto.getWinners());
        outputView.printRoundResult(racingCarGameResultDto.getRacingCars());
    }

    private List<String> readPlayerNames() {
        try {
            outputView.printNameInput();
            final String names = inputView.readCarNames();
            return splitNames(names);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readPlayerNames();
        }
    }

    private int readTryCount() {
        try {
            outputView.printCountInput();
            return inputView.readTryCount();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readTryCount();
        }
    }
}
