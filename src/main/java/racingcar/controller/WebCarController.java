package racingcar.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.service.CarService;

@RestController
public class WebCarController {

	private final CarService carService;

	public WebCarController(final CarService carService) {
		this.carService = carService;
	}

	@PostMapping("/plays")
	public ResponseEntity<GamePlayResponseDto> plays(@RequestBody GamePlayRequestDto gamePlayRequestDto) {
		final List<String> carNames = Arrays.asList(gamePlayRequestDto.getNames().split(","));
		final GamePlayResponseDto gamePlayResponseDto = carService.playGame(carNames, gamePlayRequestDto.getCount());

		return ResponseEntity.status(HttpStatus.CREATED).body(gamePlayResponseDto);
	}

	@GetMapping("/plays")
	public ResponseEntity<List<GamePlayResponseDto>> findGamePlayHistoryAll() {
		final List<GamePlayResponseDto> gamePlayHistoryAll = carService.findGamePlayHistoryAll();
		return ResponseEntity.ok().body(gamePlayHistoryAll);
	}

}
