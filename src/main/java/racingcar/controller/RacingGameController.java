package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.service.RacingGameService;

import java.util.Arrays;
import java.util.List;

import static racingcar.service.RacingGameService.GameResult;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    GameResult play(
            @RequestBody Request request
    ) {
        return racingGameService.play(request.getNames(), request.getCount());
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
}
