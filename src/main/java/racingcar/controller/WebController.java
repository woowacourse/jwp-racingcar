package racingcar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.dto.RacingCarRequestDto;
import racingcar.dto.RacingCarResponseDto;
import racingcar.service.RacingCarService;

@RestController
public class WebController {
    private final RacingCarService racingcarService;

    public WebController(RacingCarService racingcarService) {
        this.racingcarService = racingcarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingCarResponseDto> plays(@Valid @RequestBody RacingCarRequestDto request) {
        RacingCarResponseDto response = racingcarService.play(request);

        return ResponseEntity.ok()
            .body(response);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingCarResponseDto>> allGames() {
        List<RacingCarResponseDto> racingCarResponses = racingcarService.allGames();

        return ResponseEntity.ok()
            .body(racingCarResponses);
    }
}
