package racingcar.view;

import racingcar.exception.CustomException;
import racingcar.exception.ExceptionStatus;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {

    public static final String POSITION_CAR_FORMAT_SYMBOL = "-";
    public static final String POSITION_CAR_STATE_FORMAT = "%s : %s";
    private static final String WINNER_MESSAGE = "%s가 최종 우승했습니다.";
    private static final String CAR_SEPARATOR = ", ";
    private static final String LINE_BREAK = "\n";
    private static final String ERROR_PREFIX = "[ERROR] ";

    private static final Map<Integer, String> errorMessageTable;

    static {
        errorMessageTable = new HashMap<>();
        initialCarsErrorMessage();
        initialCarErrorMessage();
        initialTrackErrorMessage();
    }

    private static void initialCarsErrorMessage() {
        insertErrorMessage(new CustomException(ExceptionStatus.DUPLICATE_CAR_NAMES));
    }

    private static void initialCarErrorMessage() {
        insertErrorMessage(new CustomException(ExceptionStatus.EXCEED_CAR_NAME_LENGTH));
        insertErrorMessage(new CustomException(ExceptionStatus.HAS_BLANK_CAR_NAME));
        insertErrorMessage(new CustomException(ExceptionStatus.INVALID_CAR_NAME_FORMAT));
    }

    private static void initialTrackErrorMessage() {
        insertErrorMessage(new CustomException(ExceptionStatus.INVALID_RANGE_TRIAL_TIMES));
        insertErrorMessage(new CustomException(ExceptionStatus.INVALID_TRIAL_TIMES_FORMAT));
    }

    private static void insertErrorMessage(final CustomException customException) {
        final int errorNumber = customException.getErrorNumber();
        final String errorMessage = customException.getErrorMessage();

        errorMessageTable.put(errorNumber, errorMessage);
    }

    public void printCurrentCarsPosition(final Cars cars) {
        StringJoiner stringJoiner = new StringJoiner(LINE_BREAK);

        cars.getCarsCurrentInfo().forEach(car -> {
            stringJoiner.add(String.format(POSITION_CAR_STATE_FORMAT,
                    car.getCarName(),
                    POSITION_CAR_FORMAT_SYMBOL.repeat(car.getPosition())));
        });

        System.out.println(stringJoiner + LINE_BREAK);
    }

    public void printWinnerCars(final Cars cars) {
        String winnerCarsFormat = cars.getWinnerCars().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(CAR_SEPARATOR));

        System.out.println(String.format(WINNER_MESSAGE, winnerCarsFormat));
    }

    public void printErrorMessage(final int errorNumber) {
        System.out.println(ERROR_PREFIX + errorMessageTable.get(errorNumber));
    }
}
