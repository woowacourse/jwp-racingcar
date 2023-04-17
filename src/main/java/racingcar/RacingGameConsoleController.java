package racingcar;

import java.util.List;
import racingcar.domain.race.RacingGame;
import racingcar.domain.race.WinnerJudgeImpl;
import racingcar.dto.ResultDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {
    private final InputView inputView;
    private final OutputView outputView;

    public RacingGameConsoleController() {
        this.outputView = new OutputView();
        this.inputView = new InputView();
    }

    public void run() {
        RacingGame racingGame = startNewGame();
        racingGame.progress(readTrialCount());
        outputView.printResult(ResultDto.ofCars(racingGame.getRacingCars(), racingGame.getWinners()));
    }

    private RacingGame startNewGame() {
        return new RacingGame(readCarNames(), new WinnerJudgeImpl());
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
