package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.RacingCars;
import racingcar.dto.PostGameRequest;
import racingcar.dto.PostGameResponse;
import racingcar.dto.RacingCarDto;
import racingcar.exception.CommaNotFoundException;
import racingcar.service.RacingGameService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.addAll;
import static java.util.stream.Collectors.toList;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public PostGameResponse doGame(@RequestBody final PostGameRequest postGameRequest) {
        return racingGameService.run(sliceNameByComma(postGameRequest.getNames()), postGameRequest.getCount());
    }

    private List<String> sliceNameByComma(final String names) {
        validateComma(names);

        return Arrays.stream(names.split(","))
                .collect(toList());
    }

    private void validateComma(final String names) {
        if (!names.contains(",")) {
            throw new CommaNotFoundException();
        }
    }
}
