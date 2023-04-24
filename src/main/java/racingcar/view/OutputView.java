package racingcar.view;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final OutputView INSTANCE = new OutputView();

    private static final String PRINT_READ_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String PRINT_READ_TRY_NUM_MESSAGE = "시도할 회수는 몇회인가요?";
    private static final String PRINT_RACING_RESULT_MESSAGE = "\n실행 결과";
    private static final String PRINT_WINNERS_MESSAGE = "가 최종 우승했습니다.";

    private OutputView() {
    }

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printReadCarNamesMessage() {
        printMessage(PRINT_READ_CAR_NAMES_MESSAGE);
    }

    private void printMessage(final String message) {
        System.out.println(message);
    }

    public void printReadTryNumMessage() {
        printMessage(PRINT_READ_TRY_NUM_MESSAGE);
    }

    public void printRacingResultMessage() {
        printMessage(PRINT_RACING_RESULT_MESSAGE);
    }

    public void printCurrentRacingCarsPosition(final Map<String, Integer> carPositonMap) {
        for (final String carName : carPositonMap.keySet()) {
            printMessage(carName + " : " + carPositonMap.get(carName));
        }
        printMessage("");
    }

    public void printWinners(final List<String> winners) {
        printMessage(String.join(", ", winners) + PRINT_WINNERS_MESSAGE);
    }

    public void printErrorMessage(final String message) {
        printMessage(message);
    }
}
