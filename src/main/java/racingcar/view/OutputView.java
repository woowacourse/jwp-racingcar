package racingcar.view;

import racingcar.dto.RacingCarResponse;
import racingcar.dto.ResultResponse;

public class OutputView {

    public void printEndGameResult(ResultResponse resultResponse) {
        System.out.println("winners : " + resultResponse.getWinners());
        for (RacingCarResponse racingCarResponse : resultResponse.getRacingCars()) {
            System.out.println(racingCarResponse.getName() + " : " + racingCarResponse.getPosition());
        }
    }
}
