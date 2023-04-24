package racingcar.view;

import java.util.List;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResponse;

public class OutputView {

    private static final String RACING_CAR_INFO_FORMAT = "%s : %s";
    private static final String WINNER_INFO_FORMAT = "%s가 최종 우승했습니다.%n";
    private static final String RESULT_MESSAGE = "실행 결과";
    private static final String WINNER_INFO_DELIMITER = ", ";

    public static void printResultMessage() {
        System.out.println(System.lineSeparator() + RESULT_MESSAGE);
    }

    public static void printScoreBoard(final List<RacingCarDto> racingCars) {
        for (RacingCarDto racingCar : racingCars) {
            System.out.printf((RACING_CAR_INFO_FORMAT) + "%n", racingCar.getName(), racingCar.getPosition());
        }
    }

    private static void printWinner(final List<String> winners) {
        System.out.printf(WINNER_INFO_FORMAT, makeWinnerInfo(winners));
    }

    private static String makeWinnerInfo(List<String> winners) {
        return String.join(WINNER_INFO_DELIMITER, winners);
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printGameResult(final RacingCarResponse racingCarResponse) {
        printResultMessage();
        printWinner(racingCarResponse.getWinners());
        printScoreBoard(racingCarResponse.getRacingCars());
    }
}
