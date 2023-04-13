package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.RacingGameRequestDtoForPlays;
import racingcar.controller.dto.RacingGameResponseDto;
import racingcar.service.RacingCarService;

import java.util.List;

@RestController
public class RacingController {

    private final RacingCarService racingCarService;

    @Autowired
    public RacingController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingGameResponseDto>> query() {
        return ResponseEntity.ok()
                .body(racingCarService.query());
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponseDto> submit(@RequestBody RacingGameRequestDtoForPlays racingGameRequestDtoForPlays) {
        return ResponseEntity.ok()
                .body(racingCarService.plays(racingGameRequestDtoForPlays));
    }

}