package racingcar;

import racingcar.controller.RacingCarController;
import racingcar.repository.MemoryRacingCarRepository;
import racingcar.repository.RacingCarRepository;
import racingcar.service.RacingCarService;
import racingcar.utils.RandomNumberGenerator;

public class Application {
    public static void main(String[] args) {
        RacingCarRepository racingCarRepository = new MemoryRacingCarRepository();
        RacingCarService racingCarService = new RacingCarService(racingCarRepository, new RandomNumberGenerator());
        RacingCarController racingCarController = new RacingCarController(racingCarService);
        racingCarController.run();
    }
}
