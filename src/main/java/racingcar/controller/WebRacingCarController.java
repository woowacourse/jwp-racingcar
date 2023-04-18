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
import view.OutputView;

@RestController
public class WebRacingCarController {
	private final RacingCarDao racingCarDao = new RacingCarDao();

	@GetMapping("/plays")
	public List<ResultDto> findResult() {
		return racingCarDao.find();
	}

	@PostMapping("/plays")
	public ResultDto createData(@RequestBody RacingGameDto racingGameDto) {
		ResultDto resultDto = new ResultDto();
		String carNames = racingGameDto.getNames();
		int count = Integer.parseInt(racingGameDto.getCount());

		RacingGame racingGame = new RacingGame(carNames);
		startRacing(count, racingGame);

		Cars cars = racingGame.getCars();
		racingCarDao.insertCar(cars, count);
		resultDto = toResultDto(cars);
		OutputView.printResult(resultDto.getWinners(), resultDto.getRacingCars());
		return resultDto;
	}

	private void startRacing(int count, RacingGame racingGame) {
		for (int i = 0; i < count; i++) {
			racingGame.moveCars();
		}
	}
}
