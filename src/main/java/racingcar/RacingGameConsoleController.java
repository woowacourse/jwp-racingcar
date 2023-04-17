package racingcar;

import java.util.List;
import racingcar.dto.ResultDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RacingGameService racingGameService;

    public RacingGameConsoleController(RacingGameService racingGameService) {
        this.outputView = new OutputView();
        this.inputView = new InputView();
        this.racingGameService = racingGameService;
    }

    public void run() {
        ResultDto resultDto = racingGameService.start(readTrialCount(), readCarNames());
        outputView.printResult(resultDto);
    }

    private List<String> readCarNames() {
        while (true) {
            try {
                return inputView.readCarNames();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private int readTrialCount() {
        int tryTime;
        while (true) {
            try {
                tryTime = inputView.readTryTime();
                validateTryTime(tryTime);
                return tryTime;
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private void validateTryTime(int tryTime) {
        if (tryTime < 0) {
            throw new IllegalArgumentException("시도 횟수는 음수일 수 없습니다.");
        }
    }

}
