package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.strategy.MovingStrategy;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultResponse;
import racingcar.dto.RacingCarGameRequest;
import racingcar.service.RacingCarGameService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingCarGameWebController {

    private final RacingCarGameService racingCarGameService;
    private final MovingStrategy movingStrategy;

    @Autowired
    public RacingCarGameWebController(final RacingCarGameService racingCarGameService, final MovingStrategy movingStrategy) {
        this.racingCarGameService = racingCarGameService;
        this.movingStrategy = movingStrategy;
    }

    @PostMapping("/plays")
    public GameResultResponse play(@RequestBody RacingCarGameRequest racingCarGameRequest) {
        final int gameId = racingCarGameService.play(racingCarGameRequest.getNames(), racingCarGameRequest.getCount(), movingStrategy);

        List<CarDto> carDtos = racingCarGameService.getCars(gameId);
        final String winners = getWinners(gameId);
        return new GameResultResponse(winners, carDtos);
    }

    @GetMapping("plays")
    public List<GameResultResponse> fetchGameResults() {
        List<Integer> allGameIds = racingCarGameService.getAllGameIds();
        return allGameIds.stream()
                         .map(gameId -> new GameResultResponse(getWinners(gameId), racingCarGameService.getCars(gameId)))
                         .collect(Collectors.toUnmodifiableList());
    }

    private String getWinners(final int gameId) {
        List<String> winnerList = racingCarGameService.getWinners(gameId);
        return String.join(",", winnerList);
    }
}
