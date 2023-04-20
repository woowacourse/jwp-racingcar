package racingcar.controller;

import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class RacingGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public RacingGameController(final RacingGameService racingGameService, final InputView inputView, final OutputView outputView) {
        this.racingGameService = racingGameService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public <T> T retry(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return retry(supplier);
        }
    }

    public void run() {
        GameResponseDto responses = racingGameService.play(initialize());

        printResult(responses);
    }

    private GameRequestDto initialize() {
        List<String> carNames = retry(inputView::readCarNames);
        int count = retry(inputView::readCount);

        return new GameRequestDto(carNames, count);
    }

    private void printResult(final GameResponseDto responses) {
        outputView.printResultMessage();
        outputView.printResult(responses);
    }
}
