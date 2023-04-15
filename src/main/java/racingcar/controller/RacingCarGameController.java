package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.CarDto;
import racingcar.dto.Request;
import racingcar.dto.Response;
import racingcar.service.RacingCarGameService;

import java.util.List;

@RestController
public class RacingCarGameController {

    private final RacingCarGameService racingCarGameService;

    @Autowired
    public RacingCarGameController(final RacingCarGameService racingCarGameService) {
        this.racingCarGameService = racingCarGameService;
    }

    @PostMapping("/plays")
    public Response play(@RequestBody Request request) {
        final int gameId = racingCarGameService.play(request.getNames(), request.getCount());

        List<CarDto> carDtos = racingCarGameService.getCars(gameId);
        List<String> winnerList = racingCarGameService.getWinners(gameId);

        final String winners = String.join(",", winnerList);
        return new Response(winners, carDtos);
    }
}
