package racingcar.controller;

import static racingcar.dto.DtoMapper.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameDto;
import racingcar.dto.ResultDto;
import racingcar.jdbc.RacingCarDao;

@RestController
public class RacingCarWebController {
	private final RacingCarDao racingCarDao = new RacingCarDao();

	@GetMapping("/plays")
	public List<ResultDto> findResult() {
		return racingCarDao.find();
	}

	@PostMapping("/plays")
	public ResultDto createData(@RequestBody RacingGameDto racingGameDto) {
		String carNames = racingGameDto.getNames();
		int count = Integer.parseInt(racingGameDto.getCount());

		RacingGame racingGame = new RacingGame(carNames);
		racingGame.startRacing(count);

		Cars cars = racingGame.getCars();
		racingCarDao.save(cars, count);

		return toResultDto(cars);
	}
}
