package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameHistoryDto;
import racingcar.dto.GameRequest;
import racingcar.dto.RacingGameDto;
import racingcar.service.RacingGameService;
import racingcar.utils.Parser;

import java.util.List;

@RestController
public class RacingGameWebController {

    private static final ApplicationType applicationType = ApplicationType.WEB;

    private final RacingGameService racingGameService;
    private final Parser parser;

    public RacingGameWebController(final RacingGameService racingGameService, Parser parser) {
        this.racingGameService = racingGameService;
        this.parser = parser;
    }

    @PostMapping("/plays")
    public GameHistoryDto doGame(@RequestBody final GameRequest gameRequest) {
        RacingGameDto racingGameDto = new RacingGameDto(parser.sliceByComma(gameRequest.getNames()), gameRequest.getCount(), applicationType);
        return racingGameService.playGame(racingGameDto);
    }

    @GetMapping("/plays")
    public List<GameHistoryDto> findGame() {
        return racingGameService.findRacingGameHistory();
    }
}
