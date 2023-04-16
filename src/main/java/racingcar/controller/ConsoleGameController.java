package racingcar.controller;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import racingcar.service.GameService;
import racingcar.service.dto.ResultDto;
import racingcar.view.Command;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@Component
public class ConsoleGameController {

    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    private final GameService gameService;

    public ConsoleGameController(GameService gameService) {
        this.gameService = gameService;
    }

    public void run() {
        while (selectOperation() != Command.EXIT);
    }

    private Command selectOperation() {
        Command command = handleExceptionByRepeating(INPUT_VIEW::askCommand);
        if (command == Command.NEW) {
            play();
        }
        return command;
    }

    private void play() {
        ResultDto result = handleExceptionByRepeating(() -> {
            List<String> carNames = INPUT_VIEW.inputCarNames();
            int trialCount = INPUT_VIEW.inputTrialCount();
            return gameService.playWith(carNames, trialCount);
        });

        OUTPUT_VIEW.noticeResult();
        OUTPUT_VIEW.printStatusOf(result.getRacingCars());
        OUTPUT_VIEW.printWinners(result.getWinners());
    }

    private <T> T handleExceptionByRepeating(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception exception) {
            OUTPUT_VIEW.printError(exception.getMessage());
            return handleExceptionByRepeating(supplier);
        }
    }
}
