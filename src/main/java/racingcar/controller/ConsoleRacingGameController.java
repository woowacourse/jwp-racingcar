package racingcar.controller;

import racingcar.domain.Names;
import racingcar.domain.TryCount;
import racingcar.dto.response.GameResponseDto;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class ConsoleRacingGameController {
    private final RacingGameService racingGameService;

    public ConsoleRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    public void run() {
        Names names = retryOnInvalidUserInput(this::readCarNames);
        TryCount tryCount = retryOnInvalidUserInput(this::readTryCount);

        GameResponseDto result = racingGameService.play(names, tryCount);

        OutputView.printAllCars(result.getRacingCars());
        OutputView.printWinners(result.getWinners());
    }

    private <T> T retryOnInvalidUserInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return retryOnInvalidUserInput(request);
        }
    }

    private Names readCarNames() {
        List<String> names = InputView.readCarNames();

        return Names.from(names);
    }

    private TryCount readTryCount() {
        return TryCount.from(InputView.readCount());
    }
}
