package racingcar.view;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class OutputView {
    public static final String GAME_RESULT_FORMAT = "%s : %s\n";
    public static final String WINNER_FORMAT = "%s가 최종 우승했습니다.\n";
    private static final String POSITION_VIEW = "-";
    public static final String WINNER_DELIMITER = ", ";

    public static void printRacing(List<Car> cars) {
        for (Car car : cars) {
            int position = car.getPosition();
            String positionView = POSITION_VIEW.repeat(position);
            System.out.printf(GAME_RESULT_FORMAT, car.getName(), positionView);
        }
        System.out.println();
    }

    public static void printWinners(List<Car> cars) {
        String winners = cars.stream()
                .map(Car::getName)
                .collect(Collectors.joining(WINNER_DELIMITER));
        System.out.printf(WINNER_FORMAT, winners);
    }
}
