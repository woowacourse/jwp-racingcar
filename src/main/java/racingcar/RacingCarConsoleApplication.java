package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.repository.console.ConsoleRacingCarRepository;
import racingcar.service.RacingGameService;

public class RacingCarConsoleApplication {
	public static void main(String[] args) {
		RacingCarConsoleController controller = new RacingCarConsoleController(
			new RacingGameService(new ConsoleRacingCarRepository())
		);
		controller.run();
	}
}
