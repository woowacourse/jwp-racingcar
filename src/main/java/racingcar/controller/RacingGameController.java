package racingcar.controller;


import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Names;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.domain.TryCount;
import racingcar.view.InputView;
import racingcar.view.OutputView;


public class RacingGameController {

    private RacingCars racingCars;
    private TryCount tryCount;

    public void start() {
        setUpGame();
        playGame();
    }

    private void setUpGame() {
        racingCars = createRacingCar();
        tryCount = getTryCount();
    }

    private RacingCars createRacingCar() {
        Names names = getNames();
        return new RacingCars(createRacingCar(names));
    }

    private Names getNames() {
        try {
            return new Names(InputView.requestCarName());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getNames();
        }
    }

    private List<RacingCar> createRacingCar(Names names) {
        return names.getNames()
                .stream()
                .map(RacingCar::new)
                .collect(Collectors.toList());
    }

    private TryCount getTryCount() {
        try {
            return new TryCount(InputView.requestTryCount());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getTryCount();
        }
    }

    private void playGame() {
        OutputView.printResultMessage();

        while (canProceed()) {
            racingCars.moveAll();
            this.tryCount = tryCount.deduct();
            OutputView.printScoreBoard(racingCars);
        }

        OutputView.printWinner(racingCars);
    }

    private boolean canProceed() {
        return tryCount.isOpportunity();
    }
}
