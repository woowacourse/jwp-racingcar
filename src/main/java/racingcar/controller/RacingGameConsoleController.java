package racingcar.controller;

import org.springframework.dao.DataAccessException;
import racingcar.dto.GameHistoryDto;
import racingcar.exception.ExceptionInformation;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingGameConsoleController {

    private static final ApplicationType applicationType = ApplicationType.CONSOLE;

    private final RacingGameService racingGameService;

    public RacingGameConsoleController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    public void run() {
        while (true) {
            playRacingGame();
        }
    }

    private void playRacingGame() {
        try {
            GameHistoryDto gameHistoryDto = racingGameService.playGame(inputCarNames(), requestTryCount(), applicationType);
            OutputView.printWinner(gameHistoryDto);
        } catch (IllegalArgumentException exception) {
            ExceptionInformation exceptionInformation = ExceptionInformation.findByMessage(exception.getMessage());
            System.out.println(exceptionInformation.getExceptionMessage());
        } catch (DataAccessException exception) {
            System.out.println(ExceptionInformation.INVALID_DATABASE_ACCESS.getExceptionMessage());
        }
    }

    private List<String> inputCarNames() {
        return InputView.requestCarName();
    }

    private int requestTryCount() {
        return InputView.requestTryCount();
    }
}
