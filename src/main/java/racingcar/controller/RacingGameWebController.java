package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameHistoryDto;
import racingcar.dto.PostGameRequest;
import racingcar.dto.RacingGameDto;
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
    public GameHistoryDto doGame(@RequestBody final PostGameRequest postGameRequest) {
        RacingGameDto racingGameDto = new RacingGameDto(Parser.sliceByComma(postGameRequest.getNames()), postGameRequest.getCount(), applicationType);
        return racingGameService.playGame(racingGameDto);
    }

    @GetMapping("/plays")
    public List<GameHistoryDto> findGame() {
        return racingGameService.findRacingGameHistory();
    }
}
