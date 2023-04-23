package racingcar.controller.web;

import java.util.Arrays;
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

	private static final String REGEX = ",";
	private final RacingCarService racingCarService;

	public RacingGameController (final RacingCarService racingCarService) {
		this.racingCarService = racingCarService;
	}

	@PostMapping("/plays")
	public ResponseEntity<GameResultResponseDto> startGame (@RequestBody final StartGameRequestDto request) {
		TryCount tryCount = new TryCount(request.getCount());
		String[] carNames = request.getNames().split(REGEX);
		return new ResponseEntity<>(
				racingCarService.startRace(Arrays.stream(carNames).collect(Collectors.toList()), tryCount),
				HttpStatus.OK);
	}

	@GetMapping("/plays")
	public ResponseEntity<List<GameResultResponseDto>> showGameHistory () {
		return new ResponseEntity<>(racingCarService.findAllGameResults(), HttpStatus.OK);
	}

	@ExceptionHandler(value = {HttpMessageNotReadableException.class})
	ResponseEntity<String> handleRequestDtoValues (HttpMessageNotReadableException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

	@ExceptionHandler(value = {IllegalArgumentException.class})
	ResponseEntity<String> handleInputException (IllegalArgumentException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
