package racingcar.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingResultResponse;
import racingcar.service.RacingCarService;

@RestController
public class WebRacingCarController {
    private static final String SPLIT_DELIMITER = ",";

    private final RacingCarService racingCarService;

    public WebRacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public RacingResultResponse play(@RequestBody RacingCarRequest request) {
        List<String> carNames = getCarNames(request.getNames());
        int gameId = racingCarService.playRacingGame(carNames, request.getCount());
        return racingCarService.obtainRacingResult(gameId);
    }

    private List<String> getCarNames(String names) {
        return List.of(names.split(SPLIT_DELIMITER));
    }
}
