package racingcar.controller;

import static racingcar.dto.DtoMapper.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.NamesAndCountDto;
import racingcar.dto.ResultDto;

@RestController
public class RacingCarController {

	@PostMapping("/plays")
	public ResultDto createData(@RequestBody NamesAndCountDto namesAndCountDto) {
		String carNames = namesAndCountDto.getNames();
		int count = Integer.parseInt(namesAndCountDto.getCount());

		RacingGame racingGame = new RacingGame(carNames);
		startRacing(count, racingGame);

		Cars cars = racingGame.getCars();

		return toResultDto(cars);
	}

	public void startRacing(int count, RacingGame racingGame) {
		for (int i = 0; i < count; i++) {
			racingGame.moveCars();
		}
	}
}
