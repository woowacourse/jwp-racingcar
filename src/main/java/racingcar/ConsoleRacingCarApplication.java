package racingcar;

import java.util.Scanner;
import racingcar.controller.ConsoleRacingCarController;
import racingcar.dao.LocalCarDao;
import racingcar.dao.LocalRacingGameDao;
import racingcar.repository.RacingCarRepository;
import racingcar.repository.WebRacingCarRepository;
import racingcar.service.WebRacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        ConsoleRacingCarController controller = new ConsoleRacingCarController(initWebRacingCarService(),
                new InputView(new Scanner(System.in)),
                new OutputView());
        controller.run();
    }

    private static WebRacingCarService initWebRacingCarService() {
        return new WebRacingCarService(initWebRacingCarRepository());
    }

    private static RacingCarRepository initWebRacingCarRepository() {
        return new WebRacingCarRepository(new LocalRacingGameDao(), new LocalCarDao());
    }
}
