package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.dto.NamesAndCountDto;
import racingcar.dto.ResultDto;

@RestController
public class RacingCarController {

	@PostMapping("/plays")
	public ResultDto createData(@RequestBody NamesAndCountDto namesAndCountDTO) {
		ResultDto resultDTO = new ResultDto();
		return resultDTO;
	}
}
