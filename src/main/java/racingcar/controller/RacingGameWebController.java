package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.dto.ResultDto;
import racingcar.service.RacingCarService;

@RestController
@RequestMapping("/plays")
public class RacingGameWebController {

    private final RacingCarService racingCarService;

    public RacingGameWebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping
    public GameResponse play(@Valid @RequestBody GameRequest gameRequest) {
        ResultDto resultDto = racingCarService.play(gameRequest);
        return GameResponse.from(resultDto);
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> getAllGames() {
        List<ResultDto> results = racingCarService.findAllResults();

        List<GameResponse> gameResponses = results.stream()
                .map(GameResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(gameResponses);
    }
}
