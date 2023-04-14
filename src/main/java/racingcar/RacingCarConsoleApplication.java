package racingcar;

import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.NumberPicker;
import racingcar.domain.RandomNumberPicker;
import racingcar.util.Repeat;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(final String[] args) {
        final Cars cars = Repeat.repeatIfError(() -> new Cars(InputView.inputCarName()), OutputView::printErrorMessage);
        final Count count = Repeat.repeatIfError(() -> new Count(InputView.inputTryCount()),
                OutputView::printErrorMessage);
        race(cars, count);
        OutputView.printWinner(cars.findWinnerName());
        cleanUp();
    }

    private static void race(final Cars cars, final Count tryCount) {
        while (!tryCount.isFinished()) {
            cars.moveCars(numberPicker());
            OutputView.printStatus(cars.toDto());
            tryCount.next();
        }
    }

    private static NumberPicker numberPicker() {
        return new RandomNumberPicker();
    }

    private static void cleanUp() {
        InputView.close();
    }
}
