package racingcar.view;

import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;

import java.util.List;

public class OutputView {

    private static final String WINNER_INFO_FORMAT = "%s가 최종 우승했습니다.%n";
    private static final String RESULT_MESSAGE = "실행 결과";
    private static final String WINNER_INFO_DELIMITER = ", ";

    public static void printResultMessage() {
        System.out.println(System.lineSeparator() + RESULT_MESSAGE);
    }

    public static void printWinner(final RacingCars racingCars) {
        System.out.printf(WINNER_INFO_FORMAT, makeWinnerInfo(racingCars.getWinnerNames()));
    }

    private static String makeWinnerInfo(List<String> winners) {
        return String.join(WINNER_INFO_DELIMITER, winners);
    }
}
