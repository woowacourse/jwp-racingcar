package racingcar.controller.console;

import java.util.function.Supplier;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public RacingGameConsoleController(final InputView inputView, final OutputView outputView, final RacingGameService racingGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingGameService = racingGameService;
    }

    public void run() {
        try {
            RacingGameRequestDto requestDto = createRacingGameRequest();
            RacingGameResponseDto responseDto = racingGameService.run(requestDto);
            outputView.printResult(responseDto);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private RacingGameRequestDto createRacingGameRequest() {
        String carNames = repeatUntilNoException(inputView::inputCarNames);
        int tryCount = repeatUntilNoException(inputView::inputTryCount);
        return new RacingGameRequestDto(carNames, tryCount);
    }


    private <T>T repeatUntilNoException(Supplier<T> supplier) {
        T obj = null;
        while (obj == null) {
            try {
                obj = supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
        return obj;
    }

}
