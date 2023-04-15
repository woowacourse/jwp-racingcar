package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.service.RacingCarService;

@Controller
public class RacingCarWebController {

    private final RacingCarService racingCarService;

    public RacingCarWebController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    @ResponseBody
    public ResponseEntity<ResultResponseDto> play(@RequestBody RacingGameRequestDto request) {
        ResultResponseDto responseDto = racingCarService.play(request);
        return ResponseEntity.ok().body(responseDto);
    }
}
