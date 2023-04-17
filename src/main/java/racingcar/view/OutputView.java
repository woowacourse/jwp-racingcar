package racingcar.view;

import racingcar.dto.CarDto;
import racingcar.dto.PlayResultDto;

public class OutputView {

    private static final String CAR_STATUS_MESSAGE_FORMAT = "%s : %s\n";
    private static final String WINNERS_MESSAGE_FORMAT = "%s가 최종 우승했습니다.\n";

    public void printPlayResult(final PlayResultDto playResult) {
        System.out.printf(WINNERS_MESSAGE_FORMAT, playResult.getWinners());
        for (CarDto car : playResult.getRacingCars()) {
            System.out.printf(CAR_STATUS_MESSAGE_FORMAT, car.getName(), car.getPosition());
        }
    }
}
