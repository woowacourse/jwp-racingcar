package racingcar.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.request.RacingCarRequestDto;
import racingcar.dto.response.RacingGameResponseDto;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarApiController {

    private final RacingCarService racingCarService;

    public RacingCarApiController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public RacingGameResponseDto play(@RequestBody RacingCarRequestDto racingCarRequestDto) {
        final List<String> names = Arrays.stream(racingCarRequestDto.getNames().split(","))
                .collect(Collectors.toList());
        return racingCarService.play(
                new RacingGame(new RandomNumberGenerator(), new Cars(names), new Count(racingCarRequestDto.getCount()))
        );
    }
}
