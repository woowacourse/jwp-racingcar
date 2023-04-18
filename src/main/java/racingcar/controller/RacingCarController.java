package racingcar.controller;

import static racingcar.dto.DtoMapper.*;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.ResultDto;
import view.InputView;
import view.OutputView;

public class RacingCarController {
	private final InputView inputView = new InputView();
	private final OutputView outputView = new OutputView();

	public void run() {
		String carNames = inputView.askCars();
		int trial = inputView.askTrial();

		RacingGame racingGame = new RacingGame(carNames);
		startRacing(trial, racingGame);

		Cars cars = racingGame.getCars();
		ResultDto resultDto = toResultDto(cars);

		outputView.printResult(resultDto.getWinners(), resultDto.getRacingCars());
	}

	public void startRacing(int trial, RacingGame racingGame) {
		for (int i = 0; i < trial; i++) {
			racingGame.moveCars();
		}
	}
}
