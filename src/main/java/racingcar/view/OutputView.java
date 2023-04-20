package racingcar.view;

import racingcar.controller.dto.GameResponse;

public class OutputView {

    private static final String WINNER_INFO_FORMAT = "우승자 : %s %n";
    private static final String RESULT_MESSAGE = "실행 결과";
    private static final String PLAYER_RESULT = "  ㄴ이름 : %s%n  ㄴ이동거리 : %s%n";

    public static void printResult(final GameResponse response) {
        System.out.println(System.lineSeparator() + RESULT_MESSAGE);
        printWinner(response);
    }

    public static void printWinner(final GameResponse response) {
        System.out.printf(WINNER_INFO_FORMAT, response.getWinners());
        response.getRacingCars()
                .forEach(racingCar -> System.out.printf(PLAYER_RESULT, racingCar.getName(), racingCar.getPosition()));
    }
}
