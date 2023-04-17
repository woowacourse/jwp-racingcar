package racingcar;

import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.NumberPicker;
import racingcar.domain.RandomNumberPicker;
import racingcar.service.ConsoleRacingGameService;
import racingcar.util.Repeat;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(final String[] args) {
        final ConsoleRacingGameService gameService = new ConsoleRacingGameService(numberPicker());
        final Cars cars = Repeat.repeatIfError(() -> new Cars(InputView.inputCarName()), OutputView::printErrorMessage);
        final Count count = Repeat.repeatIfError(() -> new Count(InputView.inputTryCount()),
                OutputView::printErrorMessage);

        final RaceDto raceDto = gameService.race(cars, count);
        OutputView.printResult(raceDto);
        cleanUp();
    }

    private static NumberPicker numberPicker() {
        return new RandomNumberPicker();
    }

    private static void cleanUp() {
        InputView.close();
    }
}
