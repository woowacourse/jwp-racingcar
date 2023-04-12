package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.application.RacingGameService;

import java.util.Arrays;
import java.util.List;

import static racingcar.application.RacingGameService.CarDto;
import static racingcar.application.RacingGameService.GameResult;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    ResponseEntity<Response> play(
            @RequestBody final Request request
    ) {
        final Long id = racingGameService.play(request.getNames(), request.getCount());
        final GameResult result = racingGameService.findResultById(id);
        return ResponseEntity.ok(Response.from(result));
    }

    public static class Request {
        private String names;
        private int count;

        public List<String> getNames() {
            return Arrays.asList(names.split(","));
        }

        public int getCount() {
            return count;
        }
    }

    public static class Response {
        private final String winners;
        private final List<CarDto> racingCars;

        private Response(final List<String> winners, final List<CarDto> racingCars) {
            this.winners = String.join(",", winners);
            this.racingCars = racingCars;
        }

        public static Response from(final GameResult gameResult) {
            return new Response(gameResult.getWinners(), gameResult.getRacingCars());
        }

        public String getWinners() {
            return winners;
        }

        public List<CarDto> getRacingCars() {
            return racingCars;
        }
    }
}
