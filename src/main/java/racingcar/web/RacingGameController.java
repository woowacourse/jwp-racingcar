package racingcar.web;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.RacingGameService;
import racingcar.dto.RacingGameDto;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponse> play(@Validated @RequestBody RacingGameRequest request) {
        RacingGameDto racingGameResult = racingGameService.play(request.getCount(), request.readSplitNames());
        return ResponseEntity.ok(RacingGameResponse.from(racingGameResult));
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingGameResponse>> getHistory() {
        List<RacingGameDto> racingGameDtos = racingGameService.readGameHistory();
        List<RacingGameResponse> response = racingGameDtos.stream()
                .map(racingGameDto -> RacingGameResponse.from(racingGameDto))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

}
