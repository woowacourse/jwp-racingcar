package racingcar.controller;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultResponseDto;
import racingcar.dto.StartGameRequestDto;
import racingcar.service.RacingCarService;

@RestController
public class RacingGameController {

	private final RacingCarService racingCarService;

	public RacingGameController (final RacingCarService racingCarService) {
		this.racingCarService = racingCarService;
	}

	@PostMapping("/plays")
	public ResponseEntity<GameResultResponseDto> startGame (@RequestBody final StartGameRequestDto request) {
		TryCount tryCount = getTryCount(request.getCount());

		return new ResponseEntity<>(racingCarService.startRace(splitCarNames(request.getNames()), tryCount),
				HttpStatus.OK);
	}

	@GetMapping("/plays")
	public ResponseEntity<List<GameResultResponseDto>> showGameHistory () {

		return new ResponseEntity<>(racingCarService.findAllGameResults(), HttpStatus.OK);
	}

	private List<String> splitCarNames (final String input) {
		try {
			String[] carNames = input.split(",");
			return Arrays.stream(carNames).collect(Collectors.toList());
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

	@ExceptionHandler(value = {HttpMessageNotReadableException.class})
	ResponseEntity<String> handleRequestDtoValues (HttpMessageNotReadableException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

	@ExceptionHandler(value = {IllegalArgumentException.class, InputMismatchException.class})
	ResponseEntity<String> handleInputValues (RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
