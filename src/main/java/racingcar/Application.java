package racingcar;

import racingcar.controller.RacingController;
import racingcar.dao.RacingConsoleDao;
import racingcar.service.RacingService;

public class Application {

    public static void main(String[] args) {
        RacingController racingController = new RacingController(new RacingService(new RacingConsoleDao()));
        racingController.start();
    }
}
