package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.OneGameHistoryDto;
import racingcar.dto.PostGameRequest;
import racingcar.service.RacingGameService;
import racingcar.utils.Parser;

import java.util.List;

@RestController
public class RacingGameWebController {

    private static final ApplicationType applicationType = ApplicationType.WEB;

    private final RacingGameService racingGameService;

    public RacingGameWebController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public OneGameHistoryDto doGame(@RequestBody final PostGameRequest postGameRequest) {

        return racingGameService.playGame(Parser.sliceByComma(postGameRequest.getNames()), postGameRequest.getCount(), applicationType);
    }

    @GetMapping("/plays")
    public List<OneGameHistoryDto> findGame() {
        return racingGameService.findRacingGameHistory();
    }
}
