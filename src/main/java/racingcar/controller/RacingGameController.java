package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.application.RacingGameService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static racingcar.application.RacingGameService.CarDto;
import static racingcar.application.RacingGameService.GameResult;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    ResponseEntity<PlayGameResponse> play(
            @RequestBody final PlayGameRequest playGameRequest
    ) {
        final Long id = racingGameService.play(playGameRequest.getNames(), playGameRequest.getCount());
        final GameResult result = racingGameService.findResultById(id);
        return ResponseEntity.ok(PlayGameResponse.from(result));
    }

    @GetMapping("/plays")
    ResponseEntity<List<PlayGameResponse>> findAll() {
        return ResponseEntity.ok(racingGameService.findAll().stream()
                .map(PlayGameResponse::from)
                .collect(Collectors.toList()));
    }

    public static class PlayGameRequest {
        private String names;
        private int count;

        public List<String> getNames() {
            return Arrays.asList(names.split(","));
        }

        public int getCount() {
            return count;
        }
    }

    public static class PlayGameResponse {
        private final String winners;
        private final List<CarDto> racingCars;

        private PlayGameResponse(final List<String> winners, final List<CarDto> racingCars) {
            this.winners = String.join(",", winners);
            this.racingCars = racingCars;
        }

        public static PlayGameResponse from(final GameResult gameResult) {
            return new PlayGameResponse(gameResult.getWinners(), gameResult.getRacingCars());
        }

        public String getWinners() {
            return winners;
        }

        public List<CarDto> getRacingCars() {
            return racingCars;
        }
    }
}
