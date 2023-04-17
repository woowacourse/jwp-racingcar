package racingcar.view;

import racingcar.domain.Cars;

import java.util.List;

public class OutputView {

    public void printErrorMessage(String message) {
        System.out.println("[ERROR]" + message);
    }

    public void printResultMessage() {
        System.out.println(System.lineSeparator() + "실행 결과");
    }

    public void printResult(Cars cars) {
        cars.getCars().forEach((car)
            -> System.out.printf("%s : %d%n", car.getName(), car.getPosition())
        );
        System.out.println();
    }

    public void printWinners(List<String> winnerNames) {
        System.out.printf("%s가 최종 우승했습니다.", combineNamesWithDelimiter(winnerNames));
    }

    private static String combineNamesWithDelimiter(List<String> names) {
        return String.join(", ", names);
    }
}
