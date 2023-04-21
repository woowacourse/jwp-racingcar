package racingcar.controller;

import java.util.stream.Stream;
import racingcar.dto.GameResponseDto;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private RacingGameService racingGameService;

    public ConsoleController() {
        this.racingGameService = new RacingGameService();
    }

    public void execute() {
        try {
            String carNames = inputView.inputCarNames();
            int trialCount = inputView.inputTrialCount();
            GameResponseDto gameResponse = racingGameService.playGame(carNames, trialCount);
            showResults(gameResponse);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            execute();
        }
    }

    private void showResults(GameResponseDto gameResponse) {
        outputView.noticeResult();
        outputView.printCars(racingGameService.getCars(gameResponse.getCars()));
        outputView.printWinners(racingGameService.getWinners(gameResponse.getCars()));
    }
}
