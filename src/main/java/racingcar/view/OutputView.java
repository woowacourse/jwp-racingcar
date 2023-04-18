package racingcar.view;

import racingcar.dto.PlayerResultDto;
import racingcar.dto.RacingGameResultDto;

public class OutputView {
    public static void printResult(final RacingGameResultDto racingGameResultDto) {
        System.out.printf("우승자: %s%s", racingGameResultDto.getWinners(), System.lineSeparator());
        System.out.println();
        System.out.println("결과:");
        for (PlayerResultDto playerResultDto : racingGameResultDto.getRacingCars()) {
            System.out.printf("Name: %s, Position: %s%s", playerResultDto.getName(), playerResultDto.getPosition(), System.lineSeparator());
        }
        System.out.println();
    }
}
