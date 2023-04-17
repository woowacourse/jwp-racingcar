package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.dao.car.CarDaoConsoleImpl;
import racingcar.dao.gameresult.GameResultDaoConsoleImpl;
import racingcar.domain.movingstrategy.DefaultMovingStrategy;
import racingcar.repository.RacingGameRepository;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public final class ConsoleApplication {

    public static void main(String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            ConsoleController controller = new ConsoleController(
                    new InputView(scanner), new OutputView(),
                    new RacingGameService(
                            new RacingGameRepository(
                                    new GameResultDaoConsoleImpl(), new CarDaoConsoleImpl())));

            controller.run(new DefaultMovingStrategy());
        }
    }
}
