package racingcar;

import static racingcar.ExceptionHandlingTemplate.repeatUntilSucceed;

import racingcar.domain.race.Race;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RaceController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Race race = repeatUntilSucceed(Race::new, inputView::readCarNames, WinnerJudgeImpl::new);
        int tryTime = repeatUntilSucceed(this::validateTryTime, inputView::readTryTime);

        outputView.printResultTitle();
        while (tryTime-- > 0) {
            race.tryMoveOneTime();
            outputView.printStatus(race.getRacingCars());
        }

        outputView.printWinners(race.getWinners());
    }

    private int validateTryTime(int tryTime) {
        if (tryTime < 0) {
            throw new IllegalArgumentException("시도 횟수는 음수일 수 없습니다.");
        }
        return tryTime;
    }
}
