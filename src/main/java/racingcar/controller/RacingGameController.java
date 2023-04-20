package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.AllGameHistoryDto;
import racingcar.dto.PostGameRequest;
import racingcar.dto.OneGameHistoryDto;
import racingcar.service.RacingGameService;
import racingcar.utils.Parser;

import java.util.List;

@RestController
public class RacingGameController {

    private final RacingGameService racingGameService;

    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public OneGameHistoryDto doGame(@RequestBody final PostGameRequest postGameRequest) {
        return racingGameService.run(Parser.sliceByComma(postGameRequest.getNames()), postGameRequest.getCount());
    }

    @GetMapping("/plays")
    public List<OneGameHistoryDto> findGame() {
        return racingGameService.findRacingGameHistory();
    }
}
