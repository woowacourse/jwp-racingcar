package racingcar.controller;

import racingcar.dto.GameInfoDto;
import racingcar.dto.ResultDto;
import racingcar.services.GameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;

    public ConsoleController(InputView inputView, OutputView outputView, GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
    }

    public void run() {
        String carNames = inputView.readCarNames();
        String moveCount = inputView.readMoveCount();

        play(new GameInfoDto(carNames, moveCount));
    }

    public void play(GameInfoDto gameInfoDto) {
        try {
            ResultDto gameResult = gameService.play(gameInfoDto);
            outputView.printGameResult(gameResult);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception);
            play(gameInfoDto);
        }
    }
}
