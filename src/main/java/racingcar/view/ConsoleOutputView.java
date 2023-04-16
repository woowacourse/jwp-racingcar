package racingcar.view;

import racingcar.dto.CarDto;
import racingcar.dto.RacingResultResponseDto;

import java.util.List;

public class ConsoleOutputView {

    public static final String RESULT_MESSAGE_FORMAT = "%s : %s%n";
    public static final String WINNER_MESSAGE_FORMAT = "%s가 최종 우승했습니다.%n";
    public static final String DELIMITER = ", ";
    public static final String POSITION_BAR = "-";

    public static void printResults(List<RacingResultResponseDto> results) {
        for (RacingResultResponseDto result : results) {
            System.out.println("- - - - -");
            printResult(result);
            System.out.println("- - - - -");
        }
    }

    public static void printResult(RacingResultResponseDto result) {
        System.out.println("실행 결과");
        for (CarDto car : result.getRacingCars()) {
            System.out.printf(RESULT_MESSAGE_FORMAT, car.getName(), makePositionBar(car.getPosition()));
        }
        printWinners(result.getWinners());
    }

    private static StringBuilder makePositionBar(int position) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(POSITION_BAR.repeat(Math.max(0, position)));
        return stringBuilder;
    }

    private static void printWinners(List<String> winnerNames) {
        System.out.printf(WINNER_MESSAGE_FORMAT, combineNamesWithDelimiter(winnerNames));
    }

    private static String combineNamesWithDelimiter(List<String> names) {
        return String.join(DELIMITER, names);
    }
}
