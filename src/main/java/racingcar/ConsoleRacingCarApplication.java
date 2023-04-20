package racingcar;

import racingcar.controller.ConsoleRacingCarController;
import racingcar.repository.dao.ConsoleFindAllRecordDao;
import racingcar.repository.dao.ConsoleGameDao;
import racingcar.repository.dao.ConsolePositionDao;
import racingcar.repository.dao.ConsoleUserDao;
import racingcar.repository.dao.ConsoleWinnerDao;
import racingcar.service.FindRecordService;
import racingcar.service.MainRacingCarService;
import racingcar.service.SaveRacingCarResultService;
import racingcar.domain.RandomNumberGenerator;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        final FindRecordService findRecordService = new FindRecordService(new ConsoleFindAllRecordDao());
        final SaveRacingCarResultService saveRacingCarResultService = new SaveRacingCarResultService(
            new ConsoleUserDao(), new ConsoleGameDao(), new ConsolePositionDao(), new ConsoleWinnerDao());

        final MainRacingCarService mainService = new MainRacingCarService(
            new RandomNumberGenerator(), findRecordService, saveRacingCarResultService);

        final ConsoleRacingCarController consoleController = new ConsoleRacingCarController(mainService);
        consoleController.run();
    }
}
