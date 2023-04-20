package racingcar;

import racingcar.controller.ConsoleGameController;
import racingcar.dao.console.ConsoleCarsDao;
import racingcar.dao.console.ConsoleGamesDao;
import racingcar.domain.RandomMoveChance;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        final ConsoleGameController controller = new ConsoleGameController(
                new ConsoleGamesDao(),
                new ConsoleCarsDao(),
                new RandomMoveChance()
        );

        controller.play();
    }
}
