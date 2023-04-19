package racingcar.consolegame.ui;

import racingcar.dto.CarDto;
import racingcar.dto.RacingResponse;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/18
 */
public class OutputView {

    private static final String CAR_BASE_STATUS = "-";

    public static void error(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void startRacing() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
    }

    public void tryCount() {
        System.out.println("시도할 횟수는 몇회인가요?");
    }

    public void winner(final RacingResponse finishedCars) {
        System.out.println(finishedCars.getWinners() + "가 최종 우승했습니다.");
    }

    public void result(final RacingResponse finishedCars) {
        for (CarDto carDto : finishedCars.getRacingCars()) {
            System.out.println(carDto.getName() + ": " + CAR_BASE_STATUS.repeat(carDto.getPosition()));
        }
    }
}
