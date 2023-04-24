package racingcar.controller;

import racingcar.model.Car;
import racingcar.model.RacingCarResult;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class RacingCarConsoleController {

    private final OutputView outputView = OutputView.getInstance();
    private final InputView inputView = InputView.getInstance();
    private final RacingCarService racingCarService = new RacingCarService();

    public void run() {
        final List<String> carNames = getCarNames();
        final int trialCount = getTrialCount();

        final RacingCarResult racingCarResult = racingCarService.playRacingCar(carNames, trialCount);

        showResult(racingCarResult);
    }

    private List<String> getCarNames() {
        outputView.printReadCarNamesMessage();
        return retryWhenException(inputView::readCarNames);
    }

    private <T> T retryWhenException(final Supplier<T> supplier) {
        T result;
        do {
            result = checkIllegalArgumentException(supplier);
        } while (result == null);
        return result;
    }

    private <T> T checkIllegalArgumentException(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (final IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return null;
        }
    }

    private int getTrialCount() {
        outputView.printReadTryNumMessage();
        return retryWhenException(inputView::readTryNum);
    }

    private void showResult(final RacingCarResult racingCarResult) {
        outputView.printRacingResultMessage();
        outputView.printCurrentRacingCarsPosition(convertRacingCarsResultForPrint(racingCarResult.getRacingCars()));
        outputView.printWinners(racingCarResult.getWinners());
    }

    private Map<String, Integer> convertRacingCarsResultForPrint(final List<Car> currentCars) {
        final Map<String, Integer> racingCarsResult = new HashMap<>();
        for (final Car currentCar : currentCars) {
            racingCarsResult.put(currentCar.getName(), currentCar.getPosition());
        }
        return racingCarsResult;
    }
}
