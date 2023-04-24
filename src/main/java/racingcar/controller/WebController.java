package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingGameResultDto;
import racingcar.dto.RacingGameSetUpDto;
import racingcar.dto.view.PlayRequest;
import racingcar.dto.view.PlaySuccessResponse;
import racingcar.services.RacingGameService;

import java.util.List;

@RestController
public class WebController {

    private final RacingGameService racingGameService;

    public WebController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlaySuccessResponse> play(@RequestBody PlayRequest playRequest) {
        RacingGameResultDto racingGameDto = racingGameService.play(RacingGameSetUpDto.from(playRequest));
        return ResponseEntity.ok().body(PlaySuccessResponse.from(racingGameDto));
    }

    @GetMapping("/plays")
    public ResponseEntity<List<PlaySuccessResponse>> queryHistory() {
        List<RacingGameResultDto> racingGameDtos = racingGameService.queryHistory();
        return ResponseEntity.ok().body(PlaySuccessResponse.from(racingGameDtos));
    }

}
