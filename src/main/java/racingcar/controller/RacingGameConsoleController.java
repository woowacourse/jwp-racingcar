package racingcar.controller;

import org.springframework.dao.DataAccessException;
import racingcar.dto.OneGameHistoryDto;
import racingcar.exception.ExceptionInformation;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingGameConsoleController {

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
            OneGameHistoryDto oneGameHistoryDto = racingGameService.run(inputCarNames(), requestTryCount());
            OutputView.printWinner(oneGameHistoryDto);
        } catch (IllegalArgumentException exception) {
            ExceptionInformation exceptionInformation = ExceptionInformation.findByMessage(exception.getMessage());
            System.out.println(exceptionInformation.getExceptionMessage());
        } catch (DataAccessException exception) {
            System.out.println(ExceptionInformation.DATABASE_ACCESS_EXCEPTION.getExceptionMessage());
        }
    }

    private List<String> inputCarNames() {
        return InputView.requestCarName();
    }

    private int requestTryCount() {
        return InputView.requestTryCount();
    }
}
