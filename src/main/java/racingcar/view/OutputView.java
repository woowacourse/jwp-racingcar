package racingcar.view;

import java.util.List;

import racingcar.service.dto.CarDto;

public class OutputView {

    private static final String EXCEPTION_PREFIX = "[ERROR]";
    private static final String RESULT_START_MESSAGE = "실행 결과";
    private static final String WINNERS_POSTFIX = "가 최종 우승했습니다.";
    private static final String CAR_INFIX = " : ";
    private static final String CAR_DELIMITER = ", ";
    private static final String POSITION_CHARACTER = "-";

    public void noticeResult() {
        System.out.println(RESULT_START_MESSAGE);
    }

    public void printStatusOf(List<CarDto> cars) {
        for (CarDto car : cars) {
            printStatusOf(car);
        }
        System.out.println();
    }

    private void printStatusOf(CarDto car) {
        int fromZeroToPosition = car.getPosition();
        String progress = POSITION_CHARACTER.repeat(fromZeroToPosition);
        System.out.println(car.getName() + CAR_INFIX + progress);
    }

    public void printWinners(List<String> winnerNames) {
        System.out.println(join(winnerNames) + WINNERS_POSTFIX);
    }

    public void printError(String message) {
        System.out.println(EXCEPTION_PREFIX + message);
    }

    private String join(List<String> carNames) {
        return String.join(CAR_DELIMITER, carNames);
    }
}
