package racingcar.view;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

import static racingcar.option.Option.*;

public class OutputView {
    public static void noticeResult() {
        System.out.println("\n실행 결과");
    }

    public static void printCars(List<Car> cars) {
        for (Car car : cars) {
            printCar(car);
        }
        System.out.println();
    }

    private static void printCar(Car car) {
        System.out.print(car.getName() + CAR_INFIX + car.getPosition());
        System.out.println();
    }

    public static void printWinners(List<Car> winners) {
        List<String> winnerNames = getCarNamesOf(winners);
        String joinedNames = String.join(WINNER_DELIMITER, winnerNames);
        System.out.println(joinedNames + "가 최종 우승했습니다.");
    }

    private static List<String> getCarNamesOf(List<Car> cars) {
        return cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }
}
