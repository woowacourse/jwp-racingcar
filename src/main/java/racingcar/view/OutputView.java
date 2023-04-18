package racingcar.view;


import racingcar.dto.CarDto;

import java.util.List;

public class OutputView {

    private static final String RESULT_START_MESSAGE = "실행 결과";
    private static final String WINNERS_POSTFIX = "가 최종 우승했습니다.";
    private static final String CAR_INFIX = " : ";
    private static final String POSITION_CHARACTER = "-";

    public void noticeResult() {
        System.out.println(RESULT_START_MESSAGE);
    }

    public void printStatusOf(List<CarDto> cars) {
        for (CarDto carInfo : cars) {
            printStatusOf(carInfo);
        }
        System.out.println();
    }

    private void printStatusOf(CarDto carInfo) {
        int fromZeroToPosition = carInfo.getPosition();
        String progress = POSITION_CHARACTER.repeat(fromZeroToPosition);
        System.out.println(carInfo.getName() + CAR_INFIX + progress);
    }

    public void printWinners(final String winners) {
        System.out.println(winners + WINNERS_POSTFIX);
    }
}
