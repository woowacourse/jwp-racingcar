package racingcar.view;

import racingcar.domain.Car;
import racingcar.domain.RacingCars;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PRINT_CAR_NAME = "자동차 이름 : ";
    private static final String PRINT_POSITION_SIZE = "이동한 거리 : ";
    private static final String PRINT_WINNER = "우승자 : ";

    private void printMessage(String message) {
        System.out.println(message);
    }

    public void printCurrentRacingCarsPosition(Map<String, Integer> carPositonMap) {
        for (String carName : carPositonMap.keySet()) {
            Integer position = carPositonMap.get(carName);
            String convertPositionToDash = "-".repeat(position);
            printMessage(carName + " : " + convertPositionToDash);
        }
        printMessage("");
    }

    public void printWinners(RacingCars racingCars) {
        final String winners = racingCars.getWinners()
                                         .stream()
                                         .map(Car::getName)
                                         .collect(Collectors.joining(","));

        System.out.println(PRINT_WINNER + winners);

        racingCars.getCars()
                  .forEach(car -> System.out.println(
                          PRINT_CAR_NAME
                                  + car.getName()
                                  + PRINT_POSITION_SIZE
                                  + car.getPosition())
                  );
    }
}
