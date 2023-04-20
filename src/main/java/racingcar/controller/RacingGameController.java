package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PostGameRequest;
import racingcar.dto.PostGameResponse;
import racingcar.exception.ExceptionInformation;
import racingcar.service.RacingGameService;
import racingcar.utils.Parser;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public PostGameResponse doGame(@RequestBody final PostGameRequest postGameRequest) {
        return racingGameService.run(Parser.sliceByComma(postGameRequest.getNames()), postGameRequest.getCount());
    }
}
