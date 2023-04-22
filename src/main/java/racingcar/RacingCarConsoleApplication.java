package racingcar;

import racingcar.controller.console.RacingGameConsoleController;
import racingcar.dao.GameResultInMemoryDao;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public final class RacingCarConsoleApplication {


	public static void main (String[] args) {
		final RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(new InputView(), new OutputView(), new RacingCarService(new GameResultInMemoryDao()));
		racingGameConsoleController.run();
	}
}
