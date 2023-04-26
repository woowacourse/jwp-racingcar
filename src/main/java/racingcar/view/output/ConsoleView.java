package racingcar.view.output;

import racingcar.presentation.dto.CarData;
import racingcar.presentation.dto.GameResultResponse;

import java.util.List;

public class ConsoleView {

    private static final String RACING_RESULT_MESSAGE = System.lineSeparator() + "실행 결과";
    private static final String RACING_WINNER_MESSAGE = "%s가 최종 우승했습니다." + System.lineSeparator();
    private static final String DISTANCE_MARK = "-";
    public static final String CAR_DATA_FORMAT = "%s : %s\n";

    public void renderRacingGameResult(final List<GameResultResponse> gameResultResponses) {
        renderGameResultMessage();
        for (var gameResultResponse : gameResultResponses) {
            renderCars(gameResultResponse);
            renderWinners(gameResultResponse);
        }
    }

    private void renderGameResultMessage() {
        System.out.println(RACING_RESULT_MESSAGE);
    }

    private void renderWinners(final GameResultResponse gameResultResponse) {
        String winners = gameResultResponse.getWinners();
        System.out.printf(RACING_WINNER_MESSAGE, winners);
    }

    private void renderCars(final GameResultResponse gameResultResponse) {
        List<CarData> allCarData = gameResultResponse.getRacingCars();
        for (var carData : allCarData) {
            System.out.printf(CAR_DATA_FORMAT, carData.getName(), DISTANCE_MARK.repeat(carData.getPosition()));
        }
    }
}
