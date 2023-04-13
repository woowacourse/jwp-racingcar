package racingcar.controller;

import java.util.InputMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultResponseDto;
import racingcar.dto.StartGameRequestDto;
import racingcar.service.RacingCarService;
import racingcar.utils.CarsFactory;

@RestController
public class RacingGameController {

	private final RacingCarService racingCarService;

	public RacingGameController (final RacingCarService racingCarService) {
		this.racingCarService = racingCarService;
	}

	@PostMapping("/plays")
	public ResponseEntity<GameResultResponseDto> startGame (@RequestBody final StartGameRequestDto request) {
		Cars cars = getCars(request.getNames());
		TryCount tryCount = getTryCount(request.getCount());

		return new ResponseEntity<>(racingCarService.startRace(cars, tryCount), HttpStatus.OK);
	}

	private Cars getCars (final String input) {
		try {
			String[] carNames = input.split(",");
			return CarsFactory.createCars(carNames);
		} catch (IllegalArgumentException exception) {
			throw new IllegalArgumentException(exception.getMessage());
		}
	}

	private TryCount getTryCount (final int input) {
		try {
			return new TryCount(input);
		} catch (IllegalArgumentException | InputMismatchException exception) {
			throw new IllegalArgumentException(exception.getMessage());
		}
	}
}
