package racingcar.view.outputview;

import java.util.StringJoiner;
import java.util.stream.Collectors;
import racingcar.exception.CustomException;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;

public class OutputView {
    public static final String POSITION_CAR_FORMAT_SYMBOL = "-";
    public static final String POSITION_CAR_STATE_FORMAT = "%s : %s";
    private static final String WINNER_MESSAGE = "%s가 최종 우승했습니다.";
    private static final String CAR_SEPARATOR = ", ";
    private static final String LINE_BREAK = "\n";

    private OutputView() {
    }

    public static void printErrorMessage(CustomException exception) {
        System.out.println(exception.getMessage());
    }

    public static void printCurrentCarsPosition(final Cars cars) {
        StringJoiner stringJoiner = new StringJoiner(LINE_BREAK);

        cars.getCarsCurrentInfo().forEach(car -> {
            stringJoiner.add(String.format(POSITION_CAR_STATE_FORMAT,
                    car.getCarName(),
                    POSITION_CAR_FORMAT_SYMBOL.repeat(car.getPosition())));
        });
        System.out.println(stringJoiner + LINE_BREAK);
    }


    public static void printWinnerCars(final Cars cars) {
        String winnerCarsFormat = cars.getWinnerCars().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(CAR_SEPARATOR));

        System.out.println(String.format(WINNER_MESSAGE, winnerCarsFormat));
    }
}
