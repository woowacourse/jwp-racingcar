package racingcar.view;

import racingcar.domain.Car;
import racingcar.dto.response.CarGameResponse;
import racingcar.dto.response.CarResponse;

import java.util.List;

public class OutputView {

    public static void printBeforeRacing() {
        System.out.println("실행 결과");
    }

    public static void printResult(final CarGameResponse response) {
        System.out.println("우승자 : " + response.getWinners());
        System.out.println("결과 : ");
        for (CarResponse racingCar : response.getRacingCars()) {
            System.out.print("이름 : " + racingCar.getName());
            System.out.println(", 위치 : " + racingCar.getPosition());
        }
    }

    public static void printRacing(final List<Car> result) {
        for (Car car : result) {
            System.out.println(car.getName() + " : " + "-".repeat(car.getPosition()));
        }
        System.out.println();
    }

    public static void printWinners(final List<String> winnersName) {
        String refined = String.join(", ", winnersName);
        System.out.printf("%s가 최종 우승했습니다.\n", refined);
    }
}
