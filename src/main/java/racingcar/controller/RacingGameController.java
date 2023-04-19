package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.dao.Player;
import racingcar.domain.RacingCars;
import racingcar.service.RacingGameService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plays")
public class RacingGameController {

    private final RacingGameService racingGameService;
    private final RacingGameMapper mapper;

    public RacingGameController(final RacingGameService racingGameService, final RacingGameMapper mapper) {
        this.racingGameService = racingGameService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<GameResponse> doGame(@RequestBody final GameRequest gameRequest) {
        final RacingCars racingCars = racingGameService.run(gameRequest.getNames(), gameRequest.getCount());

        return ResponseEntity.ok(mapper.toGameResponse(racingCars));
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> findResult() {
        final Map<Long, List<Player>> results = racingGameService.findAll();
        return ResponseEntity.ok(mapper.toGameResponses(results));
    }
}
