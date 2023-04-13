package racingcar.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;
import racingcar.domain.Name;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.domain.TryCount;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {

    private RacingCars racingCars;
    private TryCount tryCount;

    public void start() {
        setUpGame();
        playGame();
    }

    private void setUpGame() {
        racingCars = createRacingCar();
        tryCount = requestTryCount();
    }

    private RacingCars createRacingCar() {
        final List<Name> names = inputCarNames();
        return new RacingCars(createRacingCar(names));
    }

    private List<Name> inputCarNames() {
        return InputView.requestCarName();
    }

    private List<RacingCar> createRacingCar(final List<Name> names) {
        return names.stream()
                .map(RacingCar::createRandomMoveRacingCar)
                .collect(toList());
    }

    private TryCount requestTryCount() {
        return InputView.requestTryCount();
    }

    private void playGame() {
        OutputView.printResultMessage();

        while (canProceed()) {
            racingCars.moveAll();
            tryCount.deduct();
            OutputView.printScoreBoard(racingCars);
        }

        OutputView.printWinner(racingCars);
    }

    private boolean canProceed() {
        return !tryCount.isZero();
    }
}
