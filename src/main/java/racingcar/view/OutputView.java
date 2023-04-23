package racingcar.view;

import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResponseDto;

public class OutputView {

    public static void printResult(RacingCarResponseDto racingCarResponseDto) {
        System.out.println("우승자 : " + racingCarResponseDto.getWinners());
        for (RacingCarDto racingCarDto : racingCarResponseDto.getRacingCars()) {
            System.out.println(racingCarDto.getName() + " : " + racingCarDto.getPosition());
        }
    }
}
