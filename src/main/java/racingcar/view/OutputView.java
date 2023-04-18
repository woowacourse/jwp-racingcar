package racingcar.view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {
    public void printWinners(List<String> winnerNames) {
        System.out.println(System.lineSeparator() + "실행 결과");
        System.out.println(winnerNames.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "))
                + "가 최종 우승했습니다." + System.lineSeparator());
    }

    public void printGameResult(Map<String, Integer> racingCars) {
        for (Entry<String, Integer> racingCar : racingCars.entrySet()) {
            System.out.println(racingCar.getKey() + ": " + racingCar.getValue());
        }
    }
}
