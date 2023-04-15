package racingcar.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.CarStatusResponseDto;
import racingcar.dto.GameHistoriesResponseDto;
import racingcar.utils.RandomPowerGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {

    private static final String CAR_NAME_DELIMITER = ",";

    private final InputView inputView;
    private final OutputView outputView;
    private final RandomPowerGenerator randomPowerGenerator;

    public RacingGameConsoleController(final InputView inputView,
                                       final OutputView outputView,
                                       final RandomPowerGenerator randomPowerGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.randomPowerGenerator = randomPowerGenerator;
    }

    public void startGame() {
        final Cars cars = getCars();
        final TryCount tryCount = getTryCount();

        startRace(cars, tryCount);

        GameHistoriesResponseDto gameHistoryDto = GameHistoriesResponseDto.toDto(findWinnerNames(cars), findCarStatuses(cars));

        outputView.printWinners(gameHistoryDto);
        outputView.printCurrentRacingStatus(gameHistoryDto);
    }

    private Cars getCars() {
        try {
            List<String> carNames = inputView.inputCarNames();
            return Cars.from(carNames);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getCars();
        }
    }

    private TryCount getTryCount() {
        try {
            int tryCount = inputView.inputTryCount();
            return new TryCount(tryCount);
        } catch (IllegalArgumentException | InputMismatchException exception) {
            System.out.println(exception.getMessage());
            return getTryCount();
        }
    }

    private void startRace(Cars cars, TryCount tryCount) {
        outputView.printResultMessage();

        for (int i = 0; i < tryCount.getTryCount(); i++) {
            cars.moveAll(randomPowerGenerator);
        }
    }

    private String findWinnerNames(final Cars cars) {
        return String.join(CAR_NAME_DELIMITER, cars.getWinnerNames());
    }

    private List<CarStatusResponseDto> findCarStatuses(final Cars cars) {
        return cars.getCars().stream()
                .map(CarStatusResponseDto::toDto)
                .collect(Collectors.toList());
    }
}
