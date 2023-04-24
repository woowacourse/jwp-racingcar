package racingcar.view;

import racingcar.controller.dto.GameResultDto;
import racingcar.controller.dto.RacingCarDto;

public class OutputView {
    private static final String PRINT_WINNER = "우승자:\n %s\n";
    public static final String PRINT_CAR_POSITION = "Name: %s, Position: %d\n";

    public static void printResult(GameResultDto gameResultDto) {
        System.out.printf(PRINT_WINNER, gameResultDto.getWinners());

        System.out.println("결과: ");
        for (RacingCarDto racingCarDto : gameResultDto.getRacingCars()) {
            System.out.printf(PRINT_CAR_POSITION, racingCarDto.getName(), racingCarDto.getPosition());
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
