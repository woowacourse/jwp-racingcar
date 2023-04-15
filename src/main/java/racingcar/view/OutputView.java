package racingcar.view;

import static java.lang.System.lineSeparator;

import racingcar.domain.Car;
import java.util.List;

public class OutputView {

    public void printResult(final List<Car> cars, final List<String> winnerNames) {
        System.out.println(lineSeparator() + "실행 결과");

        for (final Car car : cars) {
            System.out.println(car.getName() + " : " + car.getDistance());
        }

        System.out.println(String.join(",", winnerNames) + "가 최종 우승했습니다.");
    }
}
