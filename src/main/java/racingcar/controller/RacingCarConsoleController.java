package racingcar.controller;

import static racingcar.dto.DtoMapper.*;
import static racingcar.view.InputView.*;
import static racingcar.view.OutputView.*;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.ResultDto;
import service.RacingGameService;

public class RacingCarConsoleController {
	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	public void run() {
		String carNames = askCars();
		int trial = askTrial();

		RacingGame racingGame = new RacingGame(carNames);
		racingGame.startRacing(trial);

		Cars cars = racingGame.getCars();
		ResultDto resultDto = toResultDto(cars);

		printResult(resultDto.getWinners(), resultDto.getRacingCars());
	}
}
