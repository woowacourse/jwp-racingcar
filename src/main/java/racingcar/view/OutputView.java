package racingcar.view;

import java.util.List;
import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.GamePlayResponseDto;

public class OutputView {
    private final static String NEW_LINE = System.lineSeparator();
    private final static String CAR_LOCATION_INDICATOR = "-";
    private final static String PRINT_CAR_LOCATION = "%s : %s" + NEW_LINE;
    private final static String CAR_WINNER_INDICATOR = ",";
    private final static String PRINT_WINNER = "%s가 최종 우승했습니다." + NEW_LINE;
    private final static String PRINT_RESULT = NEW_LINE + "실행 결과";
    private final static String PRINT_REQUEST_CAR_NAME
            = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private final static String PRINT_REQUEST_TRY_COUNT = "시도할 회수는 몇회인가요?";

    public void printResult(GamePlayResponseDto gameResult) {
        System.out.println("----- 경주 결과 -----");
        System.out.println("우승자: " + gameResult.getWinners() + NEW_LINE);
        for (CarDto car : gameResult.getRacingCars()) {
            System.out.println(car.getName() + ": " + car.getPosition() + "칸 이동");
        }
        System.out.println("----------------------");
    }

    public void printWinner(List<String> winner) {
        System.out.printf(PRINT_WINNER, String.join(CAR_WINNER_INDICATOR,winner));
    }

    public void printResult() {
        System.out.println(PRINT_RESULT);
    }

    public void printRequestCarName() {
        System.out.println(PRINT_REQUEST_CAR_NAME);
    }

    public void printRequestTryCount() {
        System.out.println(PRINT_REQUEST_TRY_COUNT);
    }
}
