package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.RacingCars;
import racingcar.service.RacingGameService;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;
    private final RacingGameMapper mapper;

    public RacingGameController(final RacingGameService racingGameService, final RacingGameMapper mapper) {
        this.racingGameService = racingGameService;
        this.mapper = mapper;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponse> doGame(@RequestBody final GameRequest gameRequest) {
        final RacingCars racingCars = racingGameService.run(gameRequest.getNames(), gameRequest.getCount());

        return ResponseEntity.ok(mapper.toGameResponse(racingCars));
    }
}
