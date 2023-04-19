package racingcar.view;

import racingcar.domain.Car;
import racingcar.dto.response.GameResponse;
import racingcar.dto.response.CarResponse;

import java.util.List;

public class OutputView {
    public static void printBeforeRacing() {
        System.out.println("실행 결과");
    }

    public static void printRacing(final List<Car> result) {
        for (Car car : result) {
            System.out.println(car.getName() + " : " + "-".repeat(car.getPosition()));
        }
        System.out.println();
    }

    public static void printCarGameResult(final GameResponse gameResponse) {
        printWinners(gameResponse.getWinners());
        printCarResults(gameResponse.getRacingCars());
    }

    public static void printWinners(final String winnersName) {
        System.out.println(String.format("%s가 최종 우승했습니다.", winnersName));
    }

    private static void printCarResults(List<CarResponse> cars) {
        for (CarResponse carResult : cars) {
            System.out.println(String.format("%s의 최종 포지션 : %d", carResult.getName(), carResult.getPosition()));
        }
    }
}
