package racingcar.controller;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.service.RacingCarService;
import racingcar.service.dto.GameInitializeDto;
import racingcar.service.dto.RacingCarGameResultDto;

@RestController
public class WebRacingCarController {

    private final RacingCarService racingCarService;

    public WebRacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping(path = "/plays")
    public RacingCarGameResultDto playGame(@RequestBody final GameInitializeDto gameInitializeDto) {
        final RacingCarGameResultDto racingCarGameResult = racingCarService.playRound
                (splitNames(gameInitializeDto.getNames()), gameInitializeDto.getCount());
        racingCarService.saveGameResult(racingCarGameResult);
        return racingCarGameResult;
    }

    @GetMapping("/plays")
    public List<RacingCarGameResultDto> findGameHistory() {
        return racingCarService.findGameHistories();
    }

    static List<String> splitNames(final String names) {
        return stream(names.split(",", -1))
                .collect(toUnmodifiableList());
    }
}
