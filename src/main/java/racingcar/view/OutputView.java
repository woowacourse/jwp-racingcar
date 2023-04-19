package racingcar.view;

import java.util.List;
import racingcar.domain.RacingCars;

public class OutputView {

    private static final String WINNER_INFO_FORMAT = "우승자 : %s %n";
    private static final String RESULT_MESSAGE = "실행 결과";
    private static final String WINNER_INFO_DELIMITER = ", ";
    private static final String PLAYER_RESULT = "  ㄴ이름 : %s%n  ㄴ이동거리 : %s%n";

    public static void printResult(final RacingCars racingCars) {
        System.out.println(System.lineSeparator() + RESULT_MESSAGE);
        printWinner(racingCars);
    }

    public static void printWinner(final RacingCars racingCars) {
        System.out.printf(WINNER_INFO_FORMAT, makeWinnerInfo(racingCars.getWinnerNames()));
        racingCars.getRacingCars()
                .forEach(racingCar -> System.out.printf(PLAYER_RESULT, racingCar.getName(), racingCar.getPosition()));
    }

    private static String makeWinnerInfo(List<String> winners) {
        return String.join(WINNER_INFO_DELIMITER, winners);
    }
}
