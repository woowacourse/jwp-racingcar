package racingcar.view;

import racingcar.dto.ResultResponse;

public class OutputView {
    private static final String RESULT_MESSAGE = System.lineSeparator() + "실행결과";
    private static final String GAME_RESULT_MESSAGE = "가 최종 우승했습니다.";

    public void printResultMessage() {
        System.out.println(RESULT_MESSAGE);
    }

    public void printEndGameResult(ResultResponse resultResponse) {
        String winnerNames = resultResponse.getWinners();
        System.out.println(winnerNames + GAME_RESULT_MESSAGE);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
