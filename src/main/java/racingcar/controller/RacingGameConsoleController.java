package racingcar.controller;

import org.springframework.dao.DataAccessException;
import racingcar.service.dto.GameHistoryDto;
import racingcar.controller.dto.GameResponse;
import racingcar.service.dto.RacingGameDto;
import racingcar.exception.ExceptionInformation;
import racingcar.exception.CustomException;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingGameConsoleController {

    private static final ApplicationType applicationType = ApplicationType.CONSOLE;

    private final RacingGameService racingGameService;
    private final InputView inputView;
    private final OutputView outputView;

    public RacingGameConsoleController(RacingGameService racingGameService, InputView inputView, OutputView outputView) {
        this.racingGameService = racingGameService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        while (true) {
            playRacingGame();
        }
    }

    private void playRacingGame() {
        try {
            RacingGameDto racingGameDto = new RacingGameDto(inputCarNames(), requestTryCount(), applicationType);
            GameHistoryDto gameHistoryDto = racingGameService.playGame(racingGameDto);
            outputView.printWinner(GameResponse.from(gameHistoryDto));
        } catch (CustomException exception) {
            System.out.println(exception.getMessage());
        } catch (DataAccessException exception) {
            System.out.println(ExceptionInformation.INVALID_DATABASE_ACCESS.getExceptionMessage());
        }
    }

    private List<String> inputCarNames() {
        return inputView.requestCarName();
    }

    private int requestTryCount() {
        return inputView.requestTryCount();
    }
}
