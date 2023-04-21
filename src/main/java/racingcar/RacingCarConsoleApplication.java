package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.dao.RacingCarInMemoryDao;
import racingcar.service.RacingCarService;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        new RacingCarConsoleController(new RacingCarService(new RacingCarInMemoryDao())).run();
    }
}
