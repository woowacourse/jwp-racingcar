package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.car.CarStatusResponseDto;
import racingcar.dto.car.GameHistoriesResponseDto;
import racingcar.utils.RandomPowerGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

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
        final Cars cars = makeCars();
        final TryCount tryCount = makeTryCount();

        startRace(cars, tryCount);

        GameHistoriesResponseDto gameHistoryDto = GameHistoriesResponseDto.toDto(getWinnerNames(cars), convertToCarStatuses(cars));

        outputView.printWinners(gameHistoryDto);
        outputView.printCurrentRacingStatus(gameHistoryDto);
    }

    private Cars makeCars() {
        try {
            List<String> carNames = inputView.inputCarNames();
            return Cars.from(carNames);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return makeCars();
        }
    }

    private TryCount makeTryCount() {
        try {
            int tryCount = inputView.inputTryCount();
            return new TryCount(tryCount);
        } catch (IllegalArgumentException | InputMismatchException exception) {
            System.out.println(exception.getMessage());
            return makeTryCount();
        }
    }

    private void startRace(final Cars cars, final TryCount tryCount) {
        outputView.printResultMessage();

        for (int i = 0; i < tryCount.getTryCount(); i++) {
            cars.moveAll(randomPowerGenerator);
        }
    }

    private String getWinnerNames(final Cars cars) {
        return String.join(CAR_NAME_DELIMITER, cars.getWinnerNames());
    }

    private List<CarStatusResponseDto> convertToCarStatuses(final Cars cars) {
        return cars.getCars().stream()
                .map(CarStatusResponseDto::toDto)
                .collect(Collectors.toList());
    }
}
