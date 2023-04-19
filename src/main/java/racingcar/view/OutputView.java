package racingcar.view;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.cars.RacingCar;

public class OutputView {

    public void printResultTitle() {
        System.out.println("실행 결과");
    }

    public void printStatus(List<RacingCar> cars) {
        for (RacingCar car : cars) {
            System.out.println(car.getName() + " : " + car.getPosition());
        }
        System.out.println();
    }

    public void printWinners(List<RacingCar> winners) {
        List<String> winnersNames = winners.stream()
                .map(RacingCar::getName)
                .collect(Collectors.toList());
        System.out.print(String.join(", ", winnersNames));
        System.out.print("가 최종 우승했습니다.");
    }
}
