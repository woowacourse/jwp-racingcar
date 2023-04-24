package racingcar.controller;

import static racingcar.dto.DtoMapper.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.domain.Cars;
import racingcar.dto.RacingGameDto;
import racingcar.dto.ResultDto;
import racingcar.service.RacingGameService;

@RestController
public class RacingCarWebController {
	private final RacingGameService racingGameService;

	public RacingCarWebController(RacingGameService racingGameService) {
		this.racingGameService = racingGameService;
	}

	@PostMapping("/plays")
	public ResultDto createData(@RequestBody RacingGameDto racingGameDto) {
		Cars cars = racingGameService.save(racingGameDto);

		return toResultDto(cars);
	}

	@GetMapping("/plays")
	public List<ResultDto> findResult() {
		return racingGameService.find();
	}
}
