package racingcar.controller;

import static racingcar.dto.DtoMapper.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.NamesAndCountDto;
import racingcar.dto.ResultDto;
import racingcar.jdbc.RacingCarDao;

@RestController
public class RacingCarController {
	private final RacingCarDao racingCarDao = new RacingCarDao();

	@GetMapping("/plays")
	public List<ResultDto> findData(){
		return racingCarDao.find();
	}

	@PostMapping("/plays")
	public ResultDto createData(@RequestBody NamesAndCountDto namesAndCountDto) {
		String carNames = namesAndCountDto.getNames();
		int count = Integer.parseInt(namesAndCountDto.getCount());

		RacingGame racingGame = new RacingGame(carNames);
		startRacing(count, racingGame);

		Cars cars = racingGame.getCars();
		racingCarDao.insertCar(cars, count);
		return toResultDto(cars);
	}

	public void startRacing(int count, RacingGame racingGame) {
		for (int i = 0; i < count; i++) {
			racingGame.moveCars();
		}
	}
}
