package racingcar.view;

import racingcar.dto.CarDto;

import java.util.List;

public class OutputView {

    private static final String RESULT_MESSAGE = "실행 결과";
    private static final String DELIMITER = " : ";
    private static final String WINNER_MESSAGE = "가 최종 우승했습니다.";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printResultMessage() {
        System.out.println(RESULT_MESSAGE);
    }

    public static void printRaceResult(List<CarDto> raceResult) {
        raceResult.forEach(carDto -> {
            int moveCount = carDto.getPosition();
            System.out.println(carDto.getName() + DELIMITER + moveCount);
        });
        System.out.println();
    }

    public static void printFinalResult(String winners) {
        System.out.println(winners + WINNER_MESSAGE);
    }

    public static void printError(String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }
}
