package racingcar.view;

import racingcar.domain.Cars;
import racingcar.dto.CarDto;

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
            -> System.out.printf("%s : %s", car.getName(), makePositionBar(car.getPosition()))
        );
        System.out.println();
    }

    private static StringBuilder makePositionBar(int position) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int count = 0; count < position; count++) {
            stringBuilder.append("-");
        }
        return stringBuilder;
    }

    public void printWinners(List<String> winnerNames) {
        System.out.printf("%s가 최종 우승했습니다.", combineNamesWithDelimiter(winnerNames));
    }

    private static String combineNamesWithDelimiter(List<String> names) {
        return String.join(", ", names);
    }
}
