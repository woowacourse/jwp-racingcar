package racingcar.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.dto.RacingCarRequestDto;
import racingcar.dto.RacingCarResponseDto;
import racingcar.service.RacingCarService;
import racingcar.view.OutputView;

@RestController
@RequestMapping(value = "/plays")
public class RacingCarWebController {
    private final RacingCarService racingCarService;

    public RacingCarWebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RacingCarResponseDto> addRace(@RequestBody final RacingCarRequestDto racingCarRequestDto) {
        RacingCarResponseDto racingCarResponseDto = racingCarService.addRace(racingCarRequestDto);
        return ResponseEntity.ok().body(racingCarResponseDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RacingCarResponseDto>> findRace() {
        return ResponseEntity.ok().body(racingCarService.findRace());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(final Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
