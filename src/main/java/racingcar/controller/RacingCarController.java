package racingcar.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import racingcar.dto.RacingCarRequestDto;
import racingcar.dto.RacingCarResponseDto;
import racingcar.service.RacingCarService;

@Controller
public class RacingCarController {
    private final RacingCarService racingCarService;

    public RacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping(value = "/plays", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RacingCarResponseDto> raceAdd(@RequestBody final RacingCarRequestDto racingCarRequestDto) {
        return ResponseEntity.ok().body(
            racingCarService.addRace(racingCarRequestDto));
    }

    @GetMapping(value = "/plays", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RacingCarResponseDto>> raceList() {
        return ResponseEntity.ok().body(racingCarService.findRace());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(final Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
