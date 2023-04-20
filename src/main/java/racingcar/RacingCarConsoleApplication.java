package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.dao.game.RacingCarGameInMemoryDao;
import racingcar.dao.player.PlayerInMemoryDao;
import racingcar.repository.RacingCarRepository;
import racingcar.service.RacingCarService;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        RacingCarRepository repository = new RacingCarRepository(new RacingCarGameInMemoryDao(), new PlayerInMemoryDao());
        RacingCarService racingCarService = new RacingCarService(new RandomNumberGenerator(), repository);
        RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController(new InputView(), new OutputView(), racingCarService);
        racingCarConsoleController.play();
    }
}
