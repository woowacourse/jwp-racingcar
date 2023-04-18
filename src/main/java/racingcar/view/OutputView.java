package racingcar.view;

import racingcar.dto.PlayResponseDto;
import racingcar.dto.RacingCarDto;

import java.util.List;

public class OutputView {
    private static final String ERROR_HEADER = "[ERROR] ";
    public static final String WINNER_MESSAGE = "우승자는 %s 입니다.";
    public static final String RESULT_HEADER = "---결과----";
    public static final String DELIMITER = " : ";


    public static void printResult(PlayResponseDto results) {
        System.out.println(String.format(WINNER_MESSAGE, results.getWinners()));
        System.out.println(RESULT_HEADER);
        for (RacingCarDto racingCar : results.getRacingCars()) {
            System.out.println(racingCar.getName() + DELIMITER +racingCar.getPosition());
        }
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_HEADER + message);
    }
}
