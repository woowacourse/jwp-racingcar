package racingcar.view;

import racingcar.dto.CarDto;
import racingcar.dto.RacingGameResponseDto;

public class OutputView {
    private final static String PRINT_CAR_LOCATION = "%s : %d" + System.lineSeparator();
    private final static String PRINT_WINNER = "%s가 최종 우승했습니다." + System.lineSeparator();
    private final static String PRINT_REQUEST_CAR_NAME
            = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private final static String PRINT_REQUEST_TRY_COUNT = "시도할 회수는 몇회인가요?";

    public void printResult(RacingGameResponseDto racingGameResponseDto) {
        System.out.printf(PRINT_WINNER, racingGameResponseDto.getWinners());
        for (CarDto car : racingGameResponseDto.getRacingCars()) {
            System.out.printf(PRINT_CAR_LOCATION, car.getName(), car.getPosition());
        }
    }

    public void printRequestCarName() {
        System.out.println(PRINT_REQUEST_CAR_NAME);
    }

    public void printRequestTryCount() {
        System.out.println(PRINT_REQUEST_TRY_COUNT);
    }
}
