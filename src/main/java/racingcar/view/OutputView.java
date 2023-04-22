package racingcar.view;

import racingcar.controller.dto.CarStateResponse;
import racingcar.controller.dto.GameResultReponse;

import java.util.List;

public class OutputView {
    private static final String RESULT_MESSAGE = System.lineSeparator() + "실행결과";
    private static final String GAME_RESULT_MESSAGE = "가 최종 우승했습니다.";
    private static final String PATH = "-";
    private static final String RESULT_SEPARATOR = " : ";
    private static final String RESULT_NAME_DELIMITER = ", ";

    public void printResultMessage() {
        System.out.println(RESULT_MESSAGE);
    }

    public void printRoundResult(GameResultReponse gameResultReponse) {
        System.out.println(RESULT_MESSAGE);
        List<CarStateResponse> racingCars = gameResultReponse.getRacingCars();
        for (CarStateResponse racingCar : racingCars) {
            System.out.println(racingCar.getName() + RESULT_SEPARATOR + PATH.repeat(racingCar.getPosition()));
        }
        System.out.println(gameResultReponse.getWinners());
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
