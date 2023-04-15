package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.request.RacingCarRequest;
import racingcar.dto.response.RacingGameResponse;
import racingcar.service.RacingCarService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingCarApiController {

    private final RacingCarService racingCarService;

    public RacingCarApiController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingGameResponse> play(@RequestBody RacingCarRequest racingCarRequest) {
        final List<String> names = Arrays.stream(racingCarRequest.getNames().split(","))
                .collect(Collectors.toList());
        final RacingGameResponse result = racingCarService.play(
                new RacingGame(new RandomNumberGenerator(), new Cars(names), new Count(racingCarRequest.getCount()))
        );
        return ResponseEntity.ok(result);
    }
}
