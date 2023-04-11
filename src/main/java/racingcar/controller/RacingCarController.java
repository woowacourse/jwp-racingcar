package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.service.RacingCarService;

import java.util.Arrays;
import java.util.List;

import static racingcar.service.RacingCarService.GameResult;

@RestController
public class RacingCarController {

    private final RacingCarService racingCarService;

    public RacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    GameResult play(
            @RequestBody Request request
    ) {
        return racingCarService.play(request.getNames(), request.getCount());
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
