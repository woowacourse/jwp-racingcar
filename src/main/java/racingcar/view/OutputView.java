package racingcar.view;

import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingGameDto;

public class OutputView {
    public void printGameResult(RacingGameDto racingGameDto) {
        System.out.println("실행 결과");
        for (RacingCarDto car : racingGameDto.getRacingCars()) {
            System.out.println(car.getName() + " : " + car.getPosition());
        }
        System.out.println();

        String winnerNames = String.join(",", racingGameDto.getWinnerNames());
        System.out.println("우승자: " + winnerNames);
    }
}
