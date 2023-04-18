package racingcar.view.outputview;

import racingcar.exception.CustomException;
import racingcar.exception.ExceptionStatus;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;

import java.util.StringJoiner;
import java.util.stream.Collectors;

public class KoreanOutputView extends OutputView {
    public static final String POSITION_CAR_FORMAT_SYMBOL = "-";
    public static final String POSITION_CAR_STATE_FORMAT = "%s : %s";
    private static final String WINNER_MESSAGE = "%s가 최종 우승했습니다.";
    private static final String CAR_SEPARATOR = ", ";
    private static final String LINE_BREAK = "\n";

    @Override
    void initialCarsErrorMessage() {
        super.insertErrorMessage(new CustomException(ExceptionStatus.DUPLICATE_CAR_NAMES));
    }

    @Override
    void initialCarErrorMessage() {
        super.insertErrorMessage(new CustomException(ExceptionStatus.EXCEED_CAR_NAME_LENGTH));
        super.insertErrorMessage(new CustomException(ExceptionStatus.HAS_BLANK_CAR_NAME));
        super.insertErrorMessage(new CustomException(ExceptionStatus.INVALID_CAR_NAME_FORMAT));
    }

    @Override
    void initialTrackErrorMessage() {
        super.insertErrorMessage(new CustomException(ExceptionStatus.INVALID_RANGE_TRIAL_TIMES));
        super.insertErrorMessage(new CustomException(ExceptionStatus.INVALID_TRIAL_TIMES_FORMAT));
    }

    @Override
    public void printCurrentCarsPosition(final Cars cars) {
        StringJoiner stringJoiner = new StringJoiner(LINE_BREAK);

        cars.getCarsCurrentInfo().forEach(car -> {
            stringJoiner.add(String.format(POSITION_CAR_STATE_FORMAT,
                    car.getCarName(),
                    POSITION_CAR_FORMAT_SYMBOL.repeat(car.getPosition())));
        });

        System.out.println(stringJoiner + LINE_BREAK);
    }

    @Override
    public void printWinnerCars(final Cars cars) {
        String winnerCarsFormat = cars.getWinnerCars().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(CAR_SEPARATOR));

        System.out.println(String.format(WINNER_MESSAGE, winnerCarsFormat));
    }
}
