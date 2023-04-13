package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.GameResponseDto;
import racingcar.service.RacingCarService;

import java.util.List;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;

    @Autowired
    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResponseDto>> bringGameHistory() {
        return ResponseEntity.ok()
                .body(racingCarService.findAll());
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponseDto> playGame(@RequestBody GameRequestDtoForPlays gameRequestDtoForPlays) {
        return ResponseEntity.ok()
                .body(racingCarService.plays(gameRequestDtoForPlays));
    }

}
