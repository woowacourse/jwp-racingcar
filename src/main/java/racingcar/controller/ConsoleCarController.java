package racingcar.controller;

import racingcar.dto.GamePlayResponseDto;
import racingcar.service.CarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class ConsoleCarController {

    private final CarService carService;

    public ConsoleCarController(final CarService carService) {
        this.carService = carService;
    }

    public void run() {
        final List<String> carNames = retryOnError(InputView::inputCarNames);
        final int tryCount = retryOnError(InputView::inputTryCount);
        final GamePlayResponseDto gamePlayResponseDto = carService.playGame(carNames, tryCount);

        OutputView.printResult(gamePlayResponseDto);
    }

    private <T> T retryOnError(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return retryOnError(inputReader);
        }
    }
}
