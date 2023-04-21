package racingcar.view;

import racingcar.domain.Car;
import racingcar.domain.Winner;

import java.util.List;

public class Output {

    private static final String DELIMITER = ",";

    public static void print(String message) {
        System.out.println(message);
    }

    public static void printResult(Winner winner, List<Car> cars) {
        System.out.println("우승자 : " + String.join(DELIMITER, winner.getWinnerNames()));
        System.out.println();

        System.out.println("결과");
        cars.forEach(Output::printCarInformation);
    }

    private static void printCarInformation(Car car) {
        System.out.println("Name : " + car.getName() + ", Position : " + car.getDistance());
    }
}
