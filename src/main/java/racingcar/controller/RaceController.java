package racingcar.controller;

import java.util.function.Supplier;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RaceController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingCarService racingCarService;

    public RaceController(InputView inputView, OutputView outputView, RacingCarService racingCarService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingCarService = racingCarService;
    }

    public void play() {
        String carNames = repeat(inputView::readCarNames);
        int count = repeat(inputView::readCount);
        PlayRequest playRequest = new PlayRequest(carNames, count);
        PlayResponse response = racingCarService.play(playRequest);
        outputView.printResult(response);
    }

    private <T> T repeat(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return repeat(inputReader);
        }
    }
}
