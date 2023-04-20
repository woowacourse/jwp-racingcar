package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.service.RacingGameService;
import racingcar.dto.RacingGameRequest;
import racingcar.dto.ResultDto;

@RestController
@RequestMapping("/plays")
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping()
    public ResponseEntity<ResultDto> play(@RequestBody RacingGameRequest request) {
        ResultDto result = racingGameService.start(request.getCount(), request.convertToSplitedNames());
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<List<ResultDto>> viewAllGames() {
        return ResponseEntity.ok(racingGameService.findAllGameHistories());
    }

}
