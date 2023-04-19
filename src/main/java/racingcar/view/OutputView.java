package racingcar.view;

import racingcar.dto.GameResultDto;
import racingcar.dto.PlayerDto;

public class OutputView {

    public static void printResult(final GameResultDto result) {
        System.out.println("우승자: " + result.getWinners());
        for (final PlayerDto player : result.getRacingCars()) {
            System.out.println(player.getName() + "의 이동 거리: " + player.getPosition());
        }
    }
}
