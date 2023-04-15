package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultResponse;
import racingcar.dto.Request;
import racingcar.service.RacingCarGameService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingCarGameController {

    private final RacingCarGameService racingCarGameService;

    @Autowired
    public RacingCarGameController(final RacingCarGameService racingCarGameService) {
        this.racingCarGameService = racingCarGameService;
    }

    @PostMapping("/plays")
    public GameResultResponse play(@RequestBody Request request) {
        final int gameId = racingCarGameService.play(request.getNames(), request.getCount());

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
