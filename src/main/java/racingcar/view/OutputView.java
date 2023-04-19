package racingcar.view;

import racingcar.dto.CarDto;
import racingcar.dto.RacingGameResultDto;

public class OutputView {
    public static void printResult(final RacingGameResultDto racingGameResultDto) {
        System.out.printf("우승자: %s%s", racingGameResultDto.getWinners(), System.lineSeparator());
        System.out.println();
        System.out.println("결과:");
        for (CarDto carDto : racingGameResultDto.getRacingCars()) {
            System.out.printf("Name: %s, Position: %s%s", carDto.getName(), carDto.getPosition(), System.lineSeparator());
        }
        System.out.println();
    }
}
