package racingcar.controller;

import java.util.function.Supplier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import racingcar.controller.dto.GamePlayResponseDto;
import racingcar.model.Cars;
import racingcar.model.Names;
import racingcar.model.TryCount;
import racingcar.service.GameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@Component
public class ConsoleController implements CommandLineRunner {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameService gameService;

    public ConsoleController(final InputView inputView, final OutputView outputView, final GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = gameService;
    }

    @Override
    public void run(final String... args) {
        while (true) {
            final Cars cars = repeatUntilReturnValue(this::initCars);
            final TryCount tryCount = repeatUntilReturnValue(this::initTryCount);

            gameService.executeRacingGame(cars, tryCount);

            final GamePlayResponseDto gamePlayResponseDto = new GamePlayResponseDto(cars.getWinners(), cars.getCars());
            outputView.printResult(gamePlayResponseDto);
        }
    }

    private <T> T repeatUntilReturnValue(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return repeatUntilReturnValue(supplier);
        }
    }

    private Cars initCars() {
        outputView.printCarNameInputMessage();

        final Names carNames = new Names(inputView.inputCarName());
        return new Cars(carNames);
    }

    private TryCount initTryCount() {
        outputView.printTryCountInputMessage();

        return new TryCount(inputView.inputTryCount());
    }
}
