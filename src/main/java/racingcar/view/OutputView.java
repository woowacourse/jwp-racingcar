package racingcar.view;

import racingcar.dto.response.RacingCarResponse;

import java.util.List;

public class OutputView {

    private static final String RESULT_MESSAGE = "\n실행 결과";
    private static final String WINNER_MESSAGE_FORMAT = "가 최종 우승했습니다.\n";
    public static final String DISTANCE_RESULT_FORMAT = "Name: %s, Position: %s\n";

    public void printResultMessage() {
        System.out.println(RESULT_MESSAGE);
    }

    public void printFinalResult(String winners) {
        System.out.print(winners + WINNER_MESSAGE_FORMAT);
    }

    public void printDistanceResult(List<RacingCarResponse> racingCarResponses) {
        for (RacingCarResponse racingCarResponse : racingCarResponses) {
            System.out.printf(DISTANCE_RESULT_FORMAT, racingCarResponse.getName(), racingCarResponse.getPosition());
        }
    }
}
