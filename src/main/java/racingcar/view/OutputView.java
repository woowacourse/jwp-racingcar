package racingcar.view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {
    public void printNameInput() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
    }

    public void printCountInput() {
        System.out.println("시도할 회수는 몇회인가요?");
    }

    public void printResultMessage() {
        System.out.println(System.lineSeparator() + "실행 결과");
    }

    public void printWinners(List<String> winnerNames) {
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
