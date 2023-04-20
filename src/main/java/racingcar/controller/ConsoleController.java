package racingcar.controller;

import racingcar.dao.ConsoleCarDao;
import racingcar.dao.ConsoleGameDao;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.service.GameService;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final GameService racingGameService = new GameService(new RandomNumberGenerator(),
            new ConsoleCarDao(),
            new ConsoleGameDao()
    );

    public void run() {
        RacingGameRequestDto request = new RacingGameRequestDto(inputView.inputCarName(), inputView.inputTryCount());
        RacingGameResponseDto response = racingGameService.executeRacingGame(request);
        outputView.printResult(response);
    }
}
