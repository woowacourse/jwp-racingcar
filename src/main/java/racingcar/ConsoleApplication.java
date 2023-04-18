package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.service.RacingCarService;
import racingcar.view.RacingCarView;
import racingcar.view.RacingCarViewImpl;

public class ConsoleApplication {
    public static void main(String[] args) {
        RacingCarService racingCarService = new RacingCarService();
        RacingCarView racingCarView = new RacingCarViewImpl();
        ConsoleController consoleController = new ConsoleController(racingCarService, racingCarView);
        consoleController.start();
    }
}
