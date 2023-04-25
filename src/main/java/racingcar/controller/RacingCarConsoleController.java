package racingcar.controller;

import static racingcar.dto.DtoMapper.*;
import static racingcar.view.InputView.*;
import static racingcar.view.OutputView.*;

import racingcar.domain.Cars;
import racingcar.dto.RacingGameDto;
import racingcar.dto.ResultDto;
import racingcar.service.RacingGameService;

public class RacingCarConsoleController {
	private final RacingGameService racingGameService;

	public RacingCarConsoleController(RacingGameService racingGameService) {
		this.racingGameService = racingGameService;
	}

	public void run() {
		RacingGameDto racingGameDto = new RacingGameDto(askCars(), String.valueOf(askTrial()));
		Cars cars = racingGameService.save(racingGameDto);

		ResultDto resultDto = toResultDto(cars);
		printResult(resultDto.getWinners(), resultDto.getRacingCars());
	}
}
