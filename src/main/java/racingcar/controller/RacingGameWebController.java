package racingcar.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.Player;
import racingcar.domain.RacingCars;
import racingcar.service.RacingGameService;

@RestController
@RequestMapping("/plays")
public class RacingGameWebController {

    private final RacingGameService racingGameService;
    private final RacingGameMapper mapper;

    public RacingGameWebController(final RacingGameService racingGameService, final RacingGameMapper mapper) {
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
