package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameInfoDto;
import racingcar.dto.GameResultResponseDto;
import racingcar.service.RacingGameService;

// TODO: 2023-04-12 Service 계층으로 비즈니스 로직 분리
@RestController
public class WebRacingGameController {
    private final RacingGameService racingGameService;

    public WebRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponseDto> playGame(@RequestBody GameInfoDto request) {
        GameResultResponseDto response = racingGameService.play(request.getNames(), request.getCount());

        return ResponseEntity.ok(response);
    }
}
